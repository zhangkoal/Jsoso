package cn.college.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: sfpy
 * @Date: 5/15/2019 2:30 PM
 * @Descirption
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "/admin", method = RequestMethod.GET)
public class AdminController {

    @RequestMapping("/login")
    public String getMessage() {
        return "admin";
    }
}
