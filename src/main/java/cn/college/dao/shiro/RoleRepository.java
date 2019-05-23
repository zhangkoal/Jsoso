package cn.college.dao.shiro;

import cn.entity.TbRole;
import cn.entity.TbUserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: sfpy
 * @Date: 5/15/2019 8:54 AM
 * @Descirption
 * @Version 1.0
 */
@Repository
public interface RoleRepository{

    @Select("SELECT roleCode FROM V_user_role t  WHERE t.userName=#{userName}")
    List<String> getRolesByUserName(String userName);


    @Select("SELECT roleCode FROM V_user_role t  WHERE t.userName=#{userName}")
    String getRoleByUserName(String userName);

    @Select("SELECT * FROM tb_role t  WHERE t.status=10")
    List<TbRole> getRoleList();

    @Insert("insert into tb_user_role value(#{id}, #{userid}, #{roleid}, #{status}, #{createDate})")
    void addUserRoleLink(TbUserRole userRole);

    /**
     * 校验角色是否正常
     * @return
     */
    @Select("SELECT COUNT(*) FROM TB_ROLE T  WHERE T.STATUS=10 AND T.ROLECODE=#{roleCode}")
    int checkRoleByRoleCode(String roleCode);

    @Select("SELECT ID FROM TB_ROLE T WHERE T.STATUS=10 AND T.ROLECODE=#{roleCode}")
    String getRoleIdByRoleCode(String roleCode);

    @Delete("UPDATE TB_USER_ROLE SET STATUS=-1 WHERE USERiD=#{userId} AND STATUS=10")
    void deleteUserRoleLink(String userId);

}
