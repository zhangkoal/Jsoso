package cn.controller;

import cn.Util.Msg;
import cn.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailController {
    @Autowired
    private MailService mailService;


    @RequestMapping("sendMail")
    @ResponseBody
    public Msg sendMail(String toUser){
        String code = mailService.getEmailVerifCode();
        String codeMsg = "验证码:" + code;
        String title = "Jsoso：用户注册验证码";
        mailService.sendSimple(toUser, title, codeMsg);
        Msg msg = new Msg();
        msg.setCode(100);
        msg.setMsg("验证码发送成功！");
        return msg;
    }
}
