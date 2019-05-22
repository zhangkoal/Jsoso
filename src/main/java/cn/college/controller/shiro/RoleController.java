package cn.college.controller.shiro;

import cn.college.service.shiro.IRoleService;
import cn.constant.ResultStatus;
import cn.entity.TbRole;
import cn.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: sfpy
 * @Date: 5/22/2019 4:00 PM
 * @Descirption
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/api/role", method = RequestMethod.GET)
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("getAllRoles")
    public Msg getRoleList() {
        Msg msg = new Msg();
        List<TbRole> roleList = roleService.getRoleList();
        msg.setCode(ResultStatus.SUCCESS.getId());
        msg.setData(roleList);
        return msg;

    }
}
