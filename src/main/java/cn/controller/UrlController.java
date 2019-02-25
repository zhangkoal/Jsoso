package cn.controller;

import cn.constant.CommonStatus;
import cn.constant.ResultAction;
import cn.domain.LoginUserInfo;
import cn.util.*;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.PageFans;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.PageFansBean;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.javabeans.weibo.Company;
import com.qq.connect.oauth.Oauth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
public class UrlController {

    @Autowired
    private SessionUtil sessionUtil;

    @Autowired
    private BaseController baseController;

    @Autowired
    private UserIPAnalysis userIPAnalysis;

    @Autowired
    private QQUtil qqUtil;

    @Value("${web.qqCallBackUrl}")
    private String qqCallBackUrl;


    @RequestMapping(value = "",method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        return "index";
    }

    /**
     * @Author zhangk
     * @Description QQ登录
     * @Date 2019/2/21 22:38
     * @Param
     * @return
     **/
    @RequestMapping("callback")
    public void index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResultAction userInfo = qqUtil.getUserInfoBean(request);
        if( userInfo.getData() != null) {
            UserInfoBean userInfoBean = (UserInfoBean) userInfo.getData();
            String userName = userInfoBean.getNickname();

            String userLoginUUID = UUIDGenerator.getUUID();

            String openId = request.getSession().getAttribute("demo_openid") + "";
            sessionUtil.insertUserSession( openId, userLoginUUID);
            LoginUserInfo user = new LoginUserInfo();
            //登录头像
            user.setImgUrl(userInfoBean.getAvatar().getAvatarURL50());
            user.setUserName(userName);

            String loginIp = baseController.getIP(request);
            userIPAnalysis.ipAnalysis(loginIp);


            Cookie cookie = new Cookie("jsoso", openId);
            cookie.setMaxAge(60 * 30);
            response.addCookie(cookie);

            Cookie cookietype = new Cookie("jsoso_loginType", "QQ");
            cookietype.setMaxAge(60 * 30);
            response.setHeader("P3P","CP=\"NON DSP COR CURa ADMa DEVa TAIa PSAa PSDa IVAa IVDa CONa HISa TELa OTPa OUR UNRa IND UNI COM NAV INT DEM CNT PRE LOC\"");
            response.addCookie(cookietype);

            System.out.println("--------------------------设置cookie-------------------------------");
        };
        response.sendRedirect(qqCallBackUrl);
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String loginUrl(HttpServletRequest request) {
        return "login";
    }

    @RequestMapping(value = "register",method = RequestMethod.GET)
    public String register(HttpServletRequest request) {
        return "register";
    }

    /**
     * 主页面
     * @param request
     * @return
     */
    @RequestMapping(value = "index.jso",method = RequestMethod.GET)
    public String mainIndex(HttpServletRequest request) {
        return "index";
    }
}
