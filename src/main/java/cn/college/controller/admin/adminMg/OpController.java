package cn.college.controller.admin.adminMg;

import cn.college.service.user.IUserService;
import cn.entity.TbUser;
import cn.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: sfpy
 * @Date: 5/20/2019 3:17 PM
 * @Descirption
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/api/adminData", method = RequestMethod.GET)
public class OpController {

    @Autowired
    private IUserService userService;

    @RequestMapping("adminList")
    public Msg getAdminList() {
        Msg msg = new Msg();
        List<TbUser> userDataList = userService.getAllUserList();
        msg.setCode(0);
        msg.setData(userDataList);
        msg.setCount(userDataList.size());
        return msg;
    }
}
