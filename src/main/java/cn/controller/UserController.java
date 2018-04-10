package cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        return "login";
    }

    @RequestMapping(value = "register",method = RequestMethod.GET)
    public String register(HttpServletRequest request) {
        return "register";
    }
}
