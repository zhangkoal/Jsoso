package cn.controller;

import cn.Util.Msg;
import cn.Util.SessionUtil;
import cn.constant.CommonStatus;
import cn.db.UserRepositoryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    private SessionUtil sessionUtil;

    @Autowired
    private UserRepositoryController uesrController;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        return "login";
    }
    @RequestMapping(value = "register",method = RequestMethod.GET)
    public String register(HttpServletRequest request) {
        return "register";
    }

    @RequestMapping("userRegist")
    @ResponseBody
    public Msg userRegister(@RequestParam("userName")String userName,  @RequestParam("password")String password,
                        @RequestParam("emailCode")String emailCode) throws Exception {
        Msg msg = new Msg();
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
        uesrController.addUser(userName, password);
        msg.setCode(CommonStatus.NORMAL.getId());
        msg.setMsg("注册成功！");
        return msg;
    }
}
