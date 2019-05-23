package cn.sql;

import cn.college.dao.shiro.RoleRepository;
import cn.college.dao.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @Author: sfpy
 * @Date: 5/23/2019 2:26 PM
 * @Descirption
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void deleteUserByUserId() {
        userRepository.deleteUserById("B7D7F9FDC7418838BF15585847057501");
    }

    @Test
    public void deleteUserRoleLinkByUserId() {
        roleRepository.deleteUserRoleLink("B7D7F9FDC7418838BF15585847057501");
    }
}
