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

    @RequestMapping(value = "product",method = RequestMethod.GET)
    public String product(HttpServletRequest request) {
        return "product";
    }

    @RequestMapping(value = "product/list-11-1",method = RequestMethod.GET)
    public String product_11_1(HttpServletRequest request) {
        return "product/list-11-1";
    }

    @RequestMapping(value = "product/list-12-1",method = RequestMethod.GET)
    public String product_12_1(HttpServletRequest request) {
        return "product/list-12-1";
    }

    @RequestMapping(value = "product/list-13-1",method = RequestMethod.GET)
    public String product_13_1(HttpServletRequest request) {
        return "product/list-13-1";
    }

    @RequestMapping(value = "product/list-14-1",method = RequestMethod.GET)
    public String product_14_1(HttpServletRequest request) {
        return "product/list-14-1";
    }

    //案例
    @RequestMapping(value = "case/1",method = RequestMethod.GET)
    public String case_1(HttpServletRequest request) {
        return "product/case/1";
    }

    @RequestMapping(value = "case/2",method = RequestMethod.GET)
    public String case_2(HttpServletRequest request) {
        return "product/case/2";
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
    @RequestMapping(value = "introduction",method = RequestMethod.GET)
    public String caseReq(HttpServletRequest request) {
        return "introduction";
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
    @RequestMapping(value = "contact",method = RequestMethod.GET)
    public String aboutReq(HttpServletRequest request) {
        return "contact";
    }

    /**
     * footer
     */
    @RequestMapping(value = "footer",method = RequestMethod.GET)
    public String footer(HttpServletRequest request) {
        return "footer";
    }

    /**
     * header
     */
    @RequestMapping(value = "header",method = RequestMethod.GET)
    public String header(HttpServletRequest request) {
        return "header";
    }




}
