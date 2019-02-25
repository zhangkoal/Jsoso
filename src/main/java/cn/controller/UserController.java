package cn.controller;

import cn.constant.ResultAction;
import cn.constant.ResultCode;
import cn.domain.LoginUserInfo;
import cn.service.IQQUserService;
import cn.util.*;
import cn.constant.CommonStatus;
import cn.domain.TbUser;
import cn.service.IUserService;
import com.google.gson.Gson;
import com.qq.connect.oauth.Oauth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private SessionUtil sessionUtil;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IQQUserService iQQUserService;

    @Autowired
    private BaseController baseController;

    @Autowired
    private UserIPAnalysis userIPAnalysis;

    @Autowired
    private QQUtil qqUtil;


    @RequestMapping("userlogin")
    @ResponseBody
    public Msg loginIndex(String userName, String password, HttpServletRequest request) throws Exception {
        Msg msg = new Msg();
        Object userId =  iUserService.userLogin(userName, password);
        if(userId == null) {
            msg.setCode(CommonStatus.ERROR.getId());
            msg.setMsg("用户名或密码错误");
            return msg;
        }
        String userLoginUUID = UUIDGenerator.getUUID();
        sessionUtil.insertUserSession(userName, userLoginUUID);
        msg.setCode(CommonStatus.NORMAL.getId());
        msg.setData(userLoginUUID);
        msg.setUrl("index.jso");
        msg.setMsg("登陆成功");
        System.out.println("登陆成功");
        System.out.println("IP--------valid");
        String loginIp = baseController.getIP(request);
        userIPAnalysis.ipAnalysis(loginIp);
        return msg;
    }
    @RequestMapping(value = ".well-known/acme-challenge/-Ig7TTvvBvqYMRQvNwrz5dykB56LUBl9BKpmrigxdCU",method = RequestMethod.GET)
    public String  verSsl() throws Exception {
        return "ssl";
    }

    @RequestMapping(value = ".well-known/acme-challenge/sZe1pX2zYgRLvIgcnJEKN6-mM82Y6AtyIWxgm0Xu3Y8",method = RequestMethod.GET)
    public String  verSsl2() throws Exception {
        return "ssl2";
    }

    @RequestMapping("userRegist")
    @ResponseBody
    public Msg userRegister(@RequestParam("userName")String userName,  @RequestParam("password")String password,
                        @RequestParam("emailCode")String emailCode) throws Exception {
        Msg msg = new Msg();
        int count =  iUserService.getCountByUserName(userName);
        if(count > 0) {
            msg.setCode(CommonStatus.ERROR.getId());
            msg.setMsg("帐号已存在");
            return msg;
        }

        Object redisCodeVale = sessionUtil.getuserRegistSeesionValue(userName);
        if(redisCodeVale == null) {
            msg.setCode(CommonStatus.ERROR.getId());
            msg.setMsg("验证码已失效，请重新注册！");
            return msg;
        }

        if(!emailCode.toString().equalsIgnoreCase(redisCodeVale.toString())) {
            msg.setCode(CommonStatus.ERROR.getId());
            msg.setMsg("验证码输入错误，请重新输入！");
            return msg;
        }
        TbUser user = new TbUser();
        user.setUesrName(userName);
        user.setPassword(password);
        user.setUserType(1);
        user.setCreate_time(new Date());
        user.setModifiedTime(new Date());
        iUserService.save(user);
        msg.setCode(CommonStatus.NORMAL.getId());
        msg.setMsg("注册成功！");
        msg.setUrl("login");
        return msg;
    }

    /*
     * @Author zhangk
     * @Description QQ跳转
     * @Date 2019/2/22 12:23
     * @Param
     * @return
     **/
    @RequestMapping("qqLogin")
    @ResponseBody
    public String qqLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new Oauth().getAuthorizeURL(request);
    }

    /**
     * @Author zhangk
     * @Description 获取从QQ登录人员信息
     * @Date 2019/2/22 10:20
     * @Param
     * @return
     **/
    @RequestMapping("getQQUserInfo")
    @ResponseBody
    public String getUserInfoBean(HttpServletRequest request) {
        Gson gson = new Gson();
        return gson.toJson(qqUtil.getUserInfoBean(request));
    }

    /**
     * @Author zhangk
     * @Description 获取QQ用户信息通过OPENiD
     * @Date 2019/2/22 15:28
     * @Param
     * @return
     **/
    @RequestMapping("getUserInfoByOpenId")
    @ResponseBody
    public ResultAction getUserInfoByOpenId(@RequestParam("openId")String openId) {
        ResultAction resultAction = new ResultAction();

        List<LoginUserInfo> useInfo = iQQUserService.getUserInfo(openId);
        if(useInfo != null) {
            resultAction.setCode(ResultCode.OK.getCode());
            LoginUserInfo user = useInfo.get(0);
            user.setAcceeToken(null);
            user.setQqUserId(null);
            resultAction.setData(user);
        }
        return resultAction;
    }


}
