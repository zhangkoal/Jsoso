package cn.college.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailService {
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSenderImpl sender;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    /*发送邮件的方法*/
    public void sendSimple(String to, String title, String content) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", host);
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        MimeMessage mail = sender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mail);

        //设置收件人，寄件人
        messageHelper.setTo(to);
        messageHelper.setFrom(from);
        messageHelper.setSubject(title);
        messageHelper.setText(content, true);  //发送内容
       // sender.setPort(25); // 默认就是25
        sender.send(mail);

        System.out.println("邮件发送成功");

    }

}
