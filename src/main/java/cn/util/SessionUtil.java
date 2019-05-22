package cn.util;

import cn.cfg.CacheDataFactory;
import cn.constant.RedisType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 对于用户的每一次请求更新用的session时间
 */
@Component
public class SessionUtil {

    @Autowired
    private CacheDataFactory cacheDataFactory;

    @Value("${server.session.time}")
    private String sessionTime;


    /**
     * 获取用户的邮箱验证码
     * @param userName
     * @return
     */
    public Object getuserRegistSeesionValue(String userName){
        String key = RedisType.userRegistSession + userName;
       return cacheDataFactory.getCacheDataByKey(key);
    }
    /**
     * 用户注册session添加
     */
    public void insertUserRegistSession(String userName, String emailCode) throws Exception {
        String key = RedisType.userRegistSession + userName;
        //半小时内生效
        int seconds = 60 * 30;
        cacheDataFactory.updateCacheData(key, emailCode, seconds);
    }

    /**
     * 用户登陆状态session添加
     */
    public void insertUserSession(String userName, String UUID) throws Exception {
        String key = RedisType.userSession + userName;
        //半小时内生效
        int seconds = Integer.parseInt(sessionTime);
        cacheDataFactory.updateCacheData(key, UUID, seconds);
    }

    @RequestMapping("checkUser")
    @ResponseBody
    public boolean checkUserSession(String cookieValue) {
        if(cookieValue != null) {
            String[] cookieStr = cookieValue.split("-");
            if(cookieStr != null && cookieStr.length == 2) {
                Object userName = cookieStr[0];
                Object sessionValue = cookieStr[1];
                if(userName != null && sessionValue != null) {
                    String key = RedisType.userSession + userName;
                    Object value = cacheDataFactory.getCacheDataByKey(key);
                    if(value != null && value.toString().equalsIgnoreCase(sessionValue.toString())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 获取用户session时间
     * @return
     */
    @RequestMapping("getUserSession")
    public long getUserSessionTime(String cookieValue){
        if(cookieValue != null) {
            String[] cookieStr = cookieValue.split("-");
            if(cookieStr != null && cookieStr.length == 2) {
                Object userName = cookieStr[0];
                Object sessionValue = cookieStr[1];
                if(userName != null && sessionValue != null) {
                    String key = RedisType.userSession + userName;
                    long value = cacheDataFactory.getCacheDateTime(key);
                    return value;
                }
            }
        }
        return -1;
    }

    /**
     * 获取系统自定义的session时间
     * @return
     */
    @RequestMapping("sessionTime")
    @ResponseBody
    public int getSystemSessionTime(){
        return Integer.parseInt(sessionTime);
    }
}
