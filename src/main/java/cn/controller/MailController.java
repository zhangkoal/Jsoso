package cn.controller;

import cn.Util.Msg;
import cn.Util.UUIDGenerator;
import cn.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;

@Controller
public class MailController {
    @Autowired
    private MailService mailService;

    @Value("${web.name}")
    private String webName;


    @RequestMapping("sendMail")
    @ResponseBody
    public Msg sendMail(@RequestParam("toUser")String toUser) throws MessagingException {
        String code = getEmailVerifCode();
        System.out.println(code);
        String codeMsg = "尊敬的用户：您的校验码： <h1 style='color:red'>" + code + "</h1>工作人员不会索取，请勿泄漏。<br><br><br><br><br><br><br>" +
                "<br><br>"+ webName + "团队";
        String title = webName + "校验码";
        mailService.sendSimple(toUser, title, codeMsg);
        Msg msg = new Msg();
        msg.setCode(100);
        msg.setMsg("验证码发送成功！");
        return msg;
    }

    public String getEmailVerifCode() {
        return  UUIDGenerator.getUUID().substring(0, 5);
    }
}
