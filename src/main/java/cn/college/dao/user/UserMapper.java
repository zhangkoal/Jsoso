package cn.college.dao.user;

import cn.entity.TbUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author: sfpy
 * @Date: 5/15/2019 11:28 AM
 * @Descirption
 * @Version 1.0
 */
@Repository
public interface UserMapper {

    @Select("SELECT count(*) FROM tb_user t  WHERE t.userName=#{userName} and t.password=#{password}")
    public int userLogin(@Param("username") String userName, @Param("password") String password);


    @Select("SELECT count(t) FROM tb_user t  WHERE t.userName=#{userName}")
    public int getCountByUserName(@Param("username") String userName);

    @Select("SELECT * FROM tb_user t  WHERE t.userName=#{userName}")
    TbUser findByName(String userName);
}