package cn.college.serviceimp.shiro;

import cn.college.dao.shiro.RoleRepository;
import cn.college.service.shiro.IRoleService;
import cn.entity.TbRole;
import cn.entity.TbUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: sfpy
 * @Date: 5/15/2019 8:57 AM
 * @Descirption
 * @Version 1.0
 */
@Service
//事务注解
@Transactional
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

    @Override
    public void addUserRoleLink(TbUserRole userRole) {
        roleRepository.addUserRoleLink(userRole);
    }

    @Override
    public boolean checkRoleByRoleCode(String roleCode) {
        int count = roleRepository.checkRoleByRoleCode(roleCode);
        if(count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String getRoleIdByRoleCode(String roleCode) {
        return roleRepository.getRoleIdByRoleCode(roleCode);
    }

    @Override
    public void deleteUserRoleLink(String userId) {
        roleRepository.deleteUserRoleLink(userId);
    }
}
