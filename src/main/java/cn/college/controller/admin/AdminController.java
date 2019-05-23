package cn.college.controller.admin;

import cn.constant.ResultStatus;
import cn.util.Msg;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: sfpy
 * @Date: 5/17/2019 4:53 PM
 * @Descirption
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "/admin" , method = RequestMethod.GET)
public class AdminController {

    @RequestMapping(value = "logout",method = RequestMethod.GET)
    @ResponseBody
    public Msg adminLogoutReq(HttpServletRequest request) {
        Msg msg = new Msg();
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        msg.setUrl("/admin/index");
        msg.setCode(ResultStatus.SUCCESS.getId());
        return msg;
    }

    /**
     * admin-index
     */
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String indexHtml(HttpServletRequest request) {
        return "/admin/index";
    }

    /**
     * welcome
     */
    @RequestMapping(value = "pages/welcome.html",method = RequestMethod.GET)
    public String serviceReq(HttpServletRequest request) {
        return "/admin/pages/welcome";
    }

    /**
     * admin-list
     */
    @RequestMapping(value = "pages/admin/list.html",method = RequestMethod.GET)
    public String adminList(HttpServletRequest request) {
        return "/admin/pages/admin/list";
    }

    /**
     * admin-role
     */
    @RequestMapping(value = "pages/admin/role.html",method = RequestMethod.GET)
    public String adminRole(HttpServletRequest request) {
        return "/admin/pages/admin/role";
    }

    /**
     * add-admin
     */
    @RequestMapping(value = "pages/admin/add.html",method = RequestMethod.GET)
    public String addAdmin(HttpServletRequest request) {
        return "/admin/pages/admin/add";
    }

    /**
     * add-admin
     */
    @RequestMapping(value = "pages/admin/edit.html",method = RequestMethod.GET)
    public String editAdmin(HttpServletRequest request) {
        return "/admin/pages/admin/edit";
    }

    /**
     * order-list
     */
    @RequestMapping(value = "pages/order/list.html",method = RequestMethod.GET)
    public String orderList(HttpServletRequest request) {
        return "/admin/pages/order/list";
    }


    /**
     * member-list
     */
    @RequestMapping(value = "pages/member/list.html",method = RequestMethod.GET)
    public String memberList(HttpServletRequest request) {
        return "/admin/pages/member/list";
    }

    /**
     * member-add
     */
    @RequestMapping(value = "pages/member/add.html",method = RequestMethod.GET)
    public String memberAdd(HttpServletRequest request) {
        return "/admin/pages/member/add";
    }

    /**
     * member-add
     */
    @RequestMapping(value = "pages/member/addInput.html",method = RequestMethod.GET)
    public String memberAddInput(HttpServletRequest request) {
        return "/admin/pages/member/addInput";
    }

    /**
     * member-del
     */
    @RequestMapping(value = "pages/member/del.html",method = RequestMethod.GET)
    public String memberDelInput(HttpServletRequest request) {
        return "/admin/pages/member/del";
    }

    /**
     * member-edit
     */
    @RequestMapping(value = "pages/member/edit.html",method = RequestMethod.GET)
    public String memberEditnput(HttpServletRequest request) {
        return "/admin/pages/member/edit";
    }




}
