package cn.college.service.user;

import cn.entity.TbUser;
import org.springframework.stereotype.Component;

/**
 * @Author: sfpy
 * @Date: 5/14/2019 11:48 AM
 * @Descirption
 * @Version 1.0
 */
@Component
public interface IUserService {

    //新增人员
    void addUser(TbUser user);

    //更新人员
    void updateUser(TbUser user);

    //删除人员
    void deleteUser(long userId);

    /**
     * 查询账号是否存在
     * @return
     */
    int getCountByUserName(String userName);

    /**
     * 用户登录验证
     * @param userName
     * @param password
     * @return
     */
    int userLogin(String userName, String password);

    TbUser getUserByUserName(String userName);


    TbUser findByName(String userName);


}
