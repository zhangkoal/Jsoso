package cn.college.controller.user;

import cn.json.AjaxResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UrlController {

    @Autowired
    private AjaxResult ajaxResult;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        return "index";
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public AjaxResult notLogin() {
        return ajaxResult.success("您尚未登陆！");
    }

    @RequestMapping(value = "/notRole", method = RequestMethod.GET)
    public AjaxResult notRole() {
        return ajaxResult.success("您没有权限！");
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String register(HttpServletRequest request) {
        return "login";
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return "index";
    }
    /**
     * 首页
     */
    @RequestMapping(value = "case",method = RequestMethod.GET)
    public String caseReq(HttpServletRequest request) {
        return "case";
    }

    /**
     * 服务
     */
    @RequestMapping(value = "service",method = RequestMethod.GET)
    public String serviceReq(HttpServletRequest request) {
        return "service";
    }

    /**
     * 关于
     */
    @RequestMapping(value = "about",method = RequestMethod.GET)
    public String aboutReq(HttpServletRequest request) {
        return "about";
    }

    /**
     * 关于
     */
    @RequestMapping(value = "footer",method = RequestMethod.GET)
    public String footer(HttpServletRequest request) {
        return "footer";
    }





}
