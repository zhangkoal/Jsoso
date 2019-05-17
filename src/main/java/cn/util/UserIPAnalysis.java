package cn.util;

import cn.college.service.log.LogService;
import cn.entity.TbLog;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


@Slf4j
@Component
public class UserIPAnalysis {

    @Value("${swallow.ip.analysis}")
    private String ipAnalysis;

    @Autowired
    private LogService logService;

    public void ipAnalysis(String loginIp) {
        Gson gson = new Gson();
        //拼装地ip地址
        String tbUrl = ipAnalysis.split(",")[0] + loginIp;
        try {
            //获取淘宝库返回的内容
            String addr = HttpRequestUtil.get(tbUrl);
            System.out.println("taobaoIPAn:" + addr);
            //将返回的json数据转换成实体对象
            IpAnalysisCa_Tb ipAnalusisTb = gson.fromJson(addr, IpAnalysisCa_Tb.class);
            //进行请求成功与否的判断
            if(0 == ipAnalusisTb.getCode()){
                //判断是否是内网地址
               // if("XX".equalsIgnoreCase(ipAnalusisTb.getData().getRegion()))
                //日志插入
                insertLog(ipAnalusisTb);
            }
        } catch (Exception e) {
//            log.info("解析IP出错！");
//            log.info("未知");
        }
    }

    /**
     * 登陆日志插入
     * @param ipAnalysisCa_tb
     */
    public void insertLog(IpAnalysisCa_Tb ipAnalysisCa_tb) {
        IpAnalysisCa_Tb.Data data = ipAnalysisCa_tb.getData();
        TbLog tbLog = new TbLog();
        tbLog.setIp(data.getIp());
        tbLog.setCountry(data.getCountry());
        tbLog.setArea(data.getArea());
        tbLog.setRegion(data.getRegion());
        tbLog.setCity(data.getCity());
        tbLog.setCounty(data.getCounty());
        tbLog.setIsp(data.getIsp());
        tbLog.setLoginTime(new Date());
        logService.addLoginLog(tbLog);
//        log.info("IP:" + tbLog.getIp() + "-- enter web");
    }

}
