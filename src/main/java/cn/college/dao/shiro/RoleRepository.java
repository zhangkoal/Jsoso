package cn.college.dao.shiro;

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


}
