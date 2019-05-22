package cn.college.serviceimp.shiro;

import cn.college.dao.shiro.RoleRepository;
import cn.college.service.shiro.IRoleService;
import cn.entity.TbRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: sfpy
 * @Date: 5/15/2019 8:57 AM
 * @Descirption
 * @Version 1.0
 */
@Service
public class RoleServiceImp implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public List<String> getRolesByUserName(String userName) {
        return  roleRepository.getRolesByUserName(userName);
    }

    @Override
    public String getRoleByUserName(String userName) {
        return roleRepository.getRoleByUserName(userName);
    }

    @Override
    public List<TbRole> getRoleList() {
        List<TbRole> roleList = roleRepository.getRoleList();
        return roleList;
    }
}
