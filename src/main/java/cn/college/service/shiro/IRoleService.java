package cn.college.service.shiro;

import cn.entity.TbRole;
import cn.entity.TbUserRole;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: sfpy
 * @Date: 5/15/2019 8:56 AM
 * @Descirption
 * @Version 1.0
 */
@Component
public interface IRoleService {

    List<String> getRolesByUserName(String userName);

    String getRoleByUserName(String userName);

    List<TbRole> getRoleList();

    void addUserRoleLink(TbUserRole userRole);

    boolean checkRoleByRoleCode(String roleCode);

    String getRoleIdByRoleCode(String roleCode);

    void deleteUserRoleLink(String userId);




}
