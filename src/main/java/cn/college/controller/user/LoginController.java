package cn.college.controller.user;

import cn.cfg.CacheDataFactory;
import cn.college.service.shiro.IRoleService;
import cn.college.service.user.IUserService;
import cn.constant.RedisWorkspace;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

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

    @Autowired
    private CacheDataFactory cacheDataFactory;

    @Autowired
    private IUserService userService;

    // Redis服务器IP
    @Value("${sfpy.api.token}")
    public int tokenExpire;

    /**
     * 登陆
     *
     * @param user 用户
     */
    @ResponseBody
    @RequestMapping(value = "/subLogin", method = RequestMethod.POST , produces = "application/json;charset=UTF-8")
    public Msg login(TbUser user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Msg msg = new Msg();
        String userName = user.getUserName();
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, user.getPassword());
        usernamePasswordToken.setRememberMe(user.isRememberMe());
        // 执行认证登陆
        String message = null;
        try {
            subject.login(usernamePasswordToken);
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

        String token = UUID.randomUUID().toString().replace("-", "");
        TbUser userInfo = userService.getUserByUserName(userName);
        String userId = userInfo.getId();
        try {
            cacheDataFactory.updateCacheData(RedisWorkspace.API_SPACE.getValue() + token, userId, tokenExpire);
            msg.setExtData(token);
        } catch (Exception e) {
            msg.setCode(-1);
            msg.setMsg(e.getMessage());
        }
        msg.setCode(200);
        return msg;

    }
}
