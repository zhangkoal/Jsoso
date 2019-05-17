package cn.college.controller.user;

import cn.college.service.shiro.IRoleService;
import cn.entity.TbUser;
import cn.json.AjaxResult;
import cn.util.Msg;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: sfpy
 * @Date: 5/15/2019 2:31 PM
 * @Descirption
 * @Version 1.0
 */
@RestController
public class LoginController {

    private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AjaxResult ajaxResult;

    @Autowired
    private IRoleService roleService;

    /**
     * 登陆
     *
     * @param user 用户
     */
    @ResponseBody
    @RequestMapping(value = "/subLogin", method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
    public Msg login(TbUser user, HttpServletRequest request) {
        Msg msg = new Msg();
        String userName = user.getUserName();
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(userName, user.getPassword());
        token.setRememberMe(user.isRememberMe());
        // 执行认证登陆
        String message = null;
        try {
            subject.login(token);
        }catch (UnknownAccountException uae) {
            logger.info("对用户[" + userName + "]进行登录验证..验证未通过,未知账户");
              message = "未知账户";
        } catch (IncorrectCredentialsException ice) {
            logger.info("对用户[" + userName + "]进行登录验证..验证未通过,错误的凭证");
            message = "密码不正确";
        }
        if(message != null) {
            msg.setCode(-1);
            msg.setMsg(message);
            return msg;
        }
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        if(savedRequest != null && savedRequest.getRequestUrl() != null) {
            msg.setUrl(savedRequest.getRequestUrl());
        } else {
            msg.setUrl("index");
        }
        msg.setCode(200);
        return msg;
    }
}
