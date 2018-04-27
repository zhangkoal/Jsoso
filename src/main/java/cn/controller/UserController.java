package cn.controller;

import cn.Util.Msg;
import cn.Util.SessionUtil;
import cn.Util.UUIDGenerator;
import cn.constant.CommonStatus;
import cn.domain.TbUser;
import cn.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class UserController {

    @Autowired
    private SessionUtil sessionUtil;

    @Autowired
    private IUserService iUserService;

    @RequestMapping("userlogin")
    @ResponseBody
    public Msg loginIndex(String userName, String password) throws Exception {
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
        return msg;
    }
    @RequestMapping(value = ".well-known/acme-challenge/HFnuwP0v_UxXMcZMKp5kdVr48fj9avNLDOeT0HZHd7I",method = RequestMethod.GET)
    public String  verSsl() throws Exception {
        return "ssl";
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
}
