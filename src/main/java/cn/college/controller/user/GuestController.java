package cn.college.controller.user;

import cn.json.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: sfpy
 * @Date: 5/15/2019 2:55 PM
 * @Descirption 游客
 * @Version 1.0
 */
@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    private AjaxResult ajaxResult;

    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public AjaxResult login() {
        return ajaxResult.success("欢迎进入，您的身份是游客");
    }

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public AjaxResult submitLogin() {
        return ajaxResult.success("您拥有获得该接口的信息的权限！");
    }
}
