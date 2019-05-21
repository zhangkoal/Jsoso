package cn.college.controller.APIController;

import cn.cfg.CacheDataFactory;
import cn.college.service.user.IUserService;
import cn.entity.TbUser;
import cn.util.Msg;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * @Author: sfpy
 * @Date: 5/21/2019 2:24 PM
 * @Descirption 调用API的认证类
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "api")
public class ApiController {

    // Redis服务器IP
    @Value("${sfpy.api.token}")
    public int tokenExpire;

    @Autowired
    private CacheDataFactory cacheDataFactory;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/getToken", method = RequestMethod.POST)
    @ResponseBody
    public Msg getToken(String userName, String password) {
        Msg msg = new Msg();
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, password);
        subject.login(usernamePasswordToken);

        String token = UUID.randomUUID().toString().replace("-", "");
        TbUser user = userService.getUserByUserName(userName);
        String userId = user.getId();
//        try {
//            cacheDataFactory.updateCacheData(RedisWorkspace.API_SPACE.getValue() + token, userId, tokenExpire);
//            msg.setData(token);
//            msg.setCode(200);
//        } catch (Exception e) {
//            msg.setCode(-1);
//            msg.setMsg(e.getMessage());
//        }
        return msg;
    }
}
