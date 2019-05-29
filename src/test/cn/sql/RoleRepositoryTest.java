package cn.sql;

import cn.college.service.shiro.IRoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * @Author: sfpy
 * @Date: 5/15/2019 9:40 AM
 * @Descirption
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
public class RoleRepositoryTest {

    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Autowired
    private IRoleService roleService;

    @Before
    public  void Adduser(){
        //添加一个用户 密码123456 权限 admin
        simpleAccountRealm.addAccount("name","123456","admin");
    }

    @Test
    public void testSql() {
        List<String> roleList = roleService.getRolesByUserName("add");
        for(String roleCode : roleList) {
            System.out.println(roleCode);
        }
    }

    @Test
    public void testShiroAccount() {
        DefaultSecurityManager defaultSecurityManager =new DefaultSecurityManager();//创建一个默认的安全管理器
        defaultSecurityManager.setRealm(simpleAccountRealm); //将账户域添加到安全管理器中
//2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);//安全管理器提交到安全工具类中
        Subject subject=SecurityUtils.getSubject();

        UsernamePasswordToken token=new UsernamePasswordToken("name","123456");
        subject.login(token); //通过login方法和token进行认证

        subject.isAuthenticated(); //是否认证成功的方法
        System.out.println( subject.isAuthenticated());  //认证成功 输出 true
    }

}
