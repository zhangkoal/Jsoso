package cn.util;

import cn.constant.ResultAction;
import cn.constant.ResultCode;
import cn.domain.LoginUserInfo;
import cn.service.IQQUserService;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName QQUtil
 * @Description TODO
 * @Author zhangk
 * @Date 2019/2/22 11:24
 * Version 1.0
 **/
@Component
public class QQUtil {

    private static final Logger logger = LoggerFactory.getLogger(QQUtil.class);

    @Autowired
    private IQQUserService userService;


    public ResultAction getUserInfoBean(HttpServletRequest request) {
        ResultAction resultAction = new ResultAction();
        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);

            String accessToken   = null,
                    openID        = null;
            long tokenExpireIn = 0L;

            if (accessTokenObj.getAccessToken().equals("")) {
//                我们的网站被CSRF攻击了或者用户取消了授权
//                做一些数据统计工作
                System.out.print("没有获取到响应参数");
            } else {
                accessToken = accessTokenObj.getAccessToken();
                tokenExpireIn = accessTokenObj.getExpireIn();

                request.getSession().setAttribute("demo_access_token", accessToken);
                request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));

                // 利用获取到的accessToken 去获取当前用的openid -------- start
                OpenID openIDObj =  new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();

                System.out.println("欢迎你，代号为 " + openID + " 的用户!");
                request.getSession().setAttribute("demo_openid", openID);

                // 利用获取到的accessToken 去获取当前用户的openid --------- end
                logger.info("start -----------------------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 ---------------------------- start");
                UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                if (userInfoBean.getRet() == 0) {
                    //openId用户唯一标识

                    resultAction.setCode(ResultCode.OK.getCode());
                    resultAction.setData(userInfoBean);
                    userLoginBiz(userInfoBean, openID, accessToken);
                   /* System.out.println(userInfoBean.getNickname() + "<br/>");
                    System.out.println(userInfoBean.getGender() + "<br/>");
                    System.out.println("黄钻等级： " + userInfoBean.getLevel() + "<br/>");
                    System.out.println("会员 : " + userInfoBean.isVip() + "<br/>");
                    System.out.println("黄钻会员： " + userInfoBean.isYellowYearVip() + "<br/>");
                    System.out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL30() + "/><br/>");
                    System.out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL50() + "/><br/>");
                    System.out.println("<image src=" + userInfoBean.getAvatar().getAvatarURL100() + "/><br/>");*/
                } else {
                    resultAction.setCode(ResultCode.FAIL.getCode());
                    resultAction.setData(null);
                    logger.error("我们没能正确获取到您的信息，原因是:={}", userInfoBean.getMsg());
                }
            }
        } catch (QQConnectException e) {
            logger.error("查询QQ登录人员信息失败=｛｝", e);
        }
        return resultAction;
    }

    public String getUserOpenId(HttpServletRequest request) {
        ResultAction resultAction = new ResultAction();
        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);

            String accessToken   = null,
                    openID        = null;
            long tokenExpireIn = 0L;

            if (accessTokenObj.getAccessToken().equals("")) {
//                我们的网站被CSRF攻击了或者用户取消了授权
//                做一些数据统计工作
                System.out.print("没有获取到响应参数");
            } else {
                accessToken = accessTokenObj.getAccessToken();
                tokenExpireIn = accessTokenObj.getExpireIn();

                request.getSession().setAttribute("demo_access_token", accessToken);
                request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));

                // 利用获取到的accessToken 去获取当前用的openid -------- start
                OpenID openIDObj =  new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();
                return openID;

            }
        } catch (QQConnectException e) {
            logger.error("查询QQ登录人员信息失败=｛｝", e);
        }
        return null;
    }

    /**
     * @Author zhangk
     * @Description 用户登录注册业务处理
     * @Date  13:23
     * @Param
     * @return
     **/
    public void userLoginBiz(UserInfoBean userInfoBean, String openId, String accessToken) {

        //判断是否存在

        int count = userService.getCountByUserId(openId);

        String userName = userInfoBean.getNickname();
        String imgUrl = userInfoBean.getAvatar().getAvatarURL50();
        LoginUserInfo userInfo = new LoginUserInfo();
        userInfo.setUserName(userName);
        userInfo.setImgUrl(imgUrl);
        userInfo.setAcceeToken(accessToken);
        userInfo.setQqUserId(openId);
        if(count == 0) {
            userService.save(userInfo);
        }
    }
}
