package cn.college.serviceimp.user;

import cn.college.dao.user.UserMapper;
import cn.college.service.user.IUserService;
import cn.entity.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: sfpy
 * @Date: 5/14/2019 11:50 AM
 * @Descirption
 * @Version 1.0
 */
@Service
@Transactional
public class IUserServiceImp implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(TbUser user) {

    }

    @Override
    public void updateUser(TbUser user) {

    }

    @Override
    public void deleteUser(long userId) {

    }

    /**
     * 查询用户是否存在
     * @param userName
     * @return
     */
    @Override
    public int getCountByUserName(String userName) {
        return userMapper.getCountByUserName(userName);
    }

    @Override
    public int userLogin(String userName, String password) {

        return userMapper.userLogin(userName, password);
    }

    @Override
    public TbUser getUserByUserName(String userName) {
        TbUser user = userMapper.findByName(userName);
        return user;
    }


    //查询用户通过用户名
    @Override
    public TbUser findByName(String userName) {
        return userMapper.findByName(userName);
    }

}
