package cn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UrlController {
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        return "login";
    }
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String loginUrl(HttpServletRequest request) {
        return "login";
    }

    @RequestMapping(value = "register",method = RequestMethod.GET)
    public String register(HttpServletRequest request) {
        return "register";
    }

    /**
     * 主页面
     * @param request
     * @return
     */
    @RequestMapping(value = "main.jso",method = RequestMethod.GET)
    public String mainIndex(HttpServletRequest request) {
        return "main";
    }
}
