package cn.college.controller.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: sfpy
 * @Date: 5/17/2019 4:53 PM
 * @Descirption
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "admin" , method = RequestMethod.GET)
public class AdminController {

    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String adminLogoutReq(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return "/admin/index";
    }

    /**
     * 资讯管理
     */
    @RequestMapping(value = "article-list",method = RequestMethod.GET)
    public String serviceReq(HttpServletRequest request) {
        return "/admin/article-list";
    }

    /**
     * 我的桌面
     */
    @RequestMapping(value = "welcome",method = RequestMethod.GET)
    public String welcomeReq(HttpServletRequest request) {
        return "/admin/welcome";
    }

    /**
     * 角色管理
     * @return
     */

    @RequestMapping(value = "admin-role",method = RequestMethod.GET)
    public String adminRoleReq(HttpServletRequest request) {
        return "/admin/admin-role";
    }

    /**
     * 角色添加
     * @return
     */

    @RequestMapping(value = "admin-role-add",method = RequestMethod.GET)
    public String adminRoleAddReq(HttpServletRequest request) {
        return "/admin/admin-role-add";
    }

    /**
     * 权限管理
     * @return
     */

    @RequestMapping(value = "admin-permission",method = RequestMethod.GET)
    public String adminPermissionReq(HttpServletRequest request) {
        return "/admin/admin-permission";
    }

    /**
     * 管理员管理
     * @return
     */

    @RequestMapping(value = "admin-list",method = RequestMethod.GET)
    public String adminListReq(HttpServletRequest request) {
        return "/admin/admin-list";
    }

    /**
     * 会员列表
     * @return
     */

    @RequestMapping(value = "member-list",method = RequestMethod.GET)
    public String amemberListReq(HttpServletRequest request) {
        return "/admin/member-list";
    }

    /**
     * 会员列表
     * @return
     */

    @RequestMapping(value = "member-del",method = RequestMethod.GET)
    public String memberDelReq(HttpServletRequest request) {
        return "/admin/member-del";
    }

    /**
     * 成员添加
     * @param request
     * @return
     */
    @RequestMapping(value = "member-add",method = RequestMethod.GET)
    public String memberAddReq(HttpServletRequest request) {
        return "/admin/member-add";
    }

    /**
     * 等级
     * @return
     */

    @RequestMapping(value = "member-level",method = RequestMethod.GET)
    public String memberLevelReq(HttpServletRequest request) {
        return "/admin/member-level";
    }

    /**
     * 积分管理
     * @return
     */

    @RequestMapping(value = "member-scoreoperation",method = RequestMethod.GET)
    public String memberScoreoperationReq(HttpServletRequest request) {
        return "/admin/member-scoreoperation";
    }

    /**
     * 浏览记录
     * @return
     */

    @RequestMapping(value = "member-record-browse",method = RequestMethod.GET)
    public String memberRecordBrowseReq(HttpServletRequest request) {
        return "/admin/member-record-browse";
    }

    /**
     * 下载记录
     * @return
     */

    @RequestMapping(value = "member-record-download",method = RequestMethod.GET)
    public String memberRecordDownloadReq(HttpServletRequest request) {
        return "/admin/member-record-download";
    }


    /**
     * 分享记录
     * @return
     */

    @RequestMapping(value = "member-record-share",method = RequestMethod.GET)
    public String memberRecordShareReq(HttpServletRequest request) {
        return "/admin/member-record-share";
    }

    /**
     * 添加产品
     * @return
     */

    @RequestMapping(value = "product-add",method = RequestMethod.GET)
    public String productAddReq(HttpServletRequest request) {
        return "/admin/product-add";
    }

    /**
     * 添加图片
     * @return
     */
    @RequestMapping(value = "picture-add",method = RequestMethod.GET)
    public String pictureAddReq(HttpServletRequest request) {
        return "/admin/picture-add";
    }

    /**
     * 添加资讯
     * @return
     */

    @RequestMapping(value = "article-add",method = RequestMethod.GET)
    public String articleAddReq(HttpServletRequest request) {
        return "/admin/article-add";
    }




    @RequestMapping("/index")
    public String adminIndex() {
        return "admin/index";
    }

    @RequestMapping("/login")
    public String adminLogin() {
        return "admin/index";
    }
}
