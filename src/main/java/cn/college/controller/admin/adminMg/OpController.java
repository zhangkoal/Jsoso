package cn.college.controller.admin.adminMg;

import cn.college.service.shiro.IRoleService;
import cn.college.service.user.IUserService;
import cn.constant.DataStatus;
import cn.constant.ResultStatus;
import cn.entity.TbUser;
import cn.entity.TbUserRole;
import cn.util.Msg;
import cn.util.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author: sfpy
 * @Date: 5/20/2019 3:17 PM
 * @Descirption
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/api/adminData")
public class OpController {

    private  Logger logger = LoggerFactory.getLogger(OpController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "adminList", method = RequestMethod.GET)
    public Msg getAdminList() {
        Msg msg = new Msg();
        List<TbUser> userDataList = userService.getAllUserList();
        msg.setCode(0);
        msg.setData(userDataList);
        msg.setCount(userDataList.size());
        return msg;
    }

    @RequestMapping(value = "deleteAdmin", method = RequestMethod.POST)
    public Msg deleteAdmin(String userIds) {
        Msg msg = new Msg();
        try {
            userService.deleteUser(Arrays.asList(userIds.split(",")));
        }catch (RuntimeException e) {
            msg.setCode(ResultStatus.FAIL.getId());
            String message = "删除人员失败:" + e.getMessage();
            msg.setMsg(message);
            logger.error(message);
            throw new RuntimeException(message);
        }

        msg.setCode(ResultStatus.SUCCESS.getId());
        return msg;
    }

    @RequestMapping(value = "addAdmin", method = RequestMethod.POST)
    public Msg addAdmin(TbUser user) {
        Msg msg = new Msg();

        try {
            //查询账号是否存在
            int count = userService.getCountByUserName(user.getUserName());
            if(count > 0) {
                msg.setCode(ResultStatus.FAIL.getId());
                msg.setMsg("账号已存在！");
                return msg;
            }

            //添加人员
            String userId = UUIDGenerator.getUUID();
            user.setId(userId);
            Date time = new Date();
            user.setCreatDate(time);
            user.setUpdateDate(time);
            user.setStatus(DataStatus.NORMAL.getId());
            userService.addUser(user);
            //角色
            String[] roles = user.getRoles().split(",");
            for(String roleCode : roles) {
                TbUserRole userRole = new TbUserRole();
                String roleId = roleService.getRoleIdByRoleCode(roleCode);
                userRole.setId(UUIDGenerator.getUUID());
                userRole.setUserid(userId);
                userRole.setRoleid(roleId);
                userRole.setStatus(DataStatus.NORMAL.getId());
                userRole.setCreateDate(new Date());
                roleService.addUserRoleLink(userRole);
            }
        }catch (RuntimeException e) {
            msg.setCode(ResultStatus.FAIL.getId());
            msg.setMsg("添加人员失败:" + e.getMessage());
            throw new RuntimeException("添加人员失败" + e.getMessage());
        }
        msg.setCode(ResultStatus.SUCCESS.getId());
        return msg;
    }
}
