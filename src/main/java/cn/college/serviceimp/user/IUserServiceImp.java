package cn.college.serviceimp.user;

import cn.college.dao.user.UserRepository;
import cn.college.service.shiro.IRoleService;
import cn.college.service.user.IUserService;
import cn.entity.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    private UserRepository userRepository;

    @Autowired
    private IRoleService roleService;

    @Override
    public void addUser(TbUser user) {
        userRepository.addUser(user);
    }

    @Override
    public void updateUser(TbUser user) {

    }


    @Override
    public void deleteUser(List<String> userIdList) {
        for(String userId : userIdList) {
            // 删除人员
            userRepository.deleteUserById(userId);
            //删除权限
            roleService.deleteUserRoleLink(userId);
        }
    }

    /**
     * 查询用户是否存在
     * @param userName
     * @return
     */
    @Override
    public int getCountByUserName(String userName) {
        return userRepository.getCountByUserName(userName);
    }

    @Override
    public int userLogin(String userName, String password) {

        return userRepository.userLogin(userName, password);
    }

    @Override
    public TbUser getUserByUserName(String userName) {
        TbUser user = userRepository.findByName(userName);
        return user;
    }


    //查询用户通过用户名
    @Override
    public TbUser findByName(String userName) {
        return userRepository.findByName(userName);
    }

    @Override
    public List<TbUser> getAllUserList() {
        return userRepository.getAllUserList();
    }

}
