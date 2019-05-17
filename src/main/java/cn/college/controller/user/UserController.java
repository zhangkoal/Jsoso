package cn.college.controller.user;

import cn.college.service.user.IUserService;
import cn.component.IpComponent;
import cn.constant.CommonStatus;
import cn.entity.TbUser;
import cn.util.Msg;
import cn.util.SessionUtil;
import cn.util.UUIDGenerator;
import cn.util.UserIPAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    private SessionUtil sessionUtil;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IpComponent ipComponent;

    @Autowired
    private UserIPAnalysis userIPAnalysis;

    @RequestMapping("userlogin")
    @ResponseBody
    public Msg loginIndex(String userName, String password, HttpServletRequest request) throws Exception {
        Msg msg = new Msg();
        int userExist = iUserService.getCountByUserName(userName);
        if(userExist == 0) {
            msg.setCode(CommonStatus.ERROR.getId());
            msg.setMsg("用户名不存在");
            return msg;
        }
        int userCount = iUserService.userLogin(userName, password);
        if(userCount == 0) {
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
        String loginIp = ipComponent.getIP(request);
        userIPAnalysis.ipAnalysis(loginIp);
        return msg;
    }

    @RequestMapping("userRegist")
    @ResponseBody
    public Msg userRegister(@RequestParam("userName")String userName,  @RequestParam("password")String password,
                        @RequestParam("emailCode")String emailCode) throws Exception {
        Msg msg = new Msg();
        int count = iUserService.getCountByUserName(userName);
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
        iUserService.addUser(user);
        msg.setCode(CommonStatus.NORMAL.getId());
        msg.setMsg("注册成功！");
        msg.setUrl("login");
        return msg;
    }
}
