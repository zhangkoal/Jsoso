package cn.db;

import cn.domain.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRepositoryController {

    @Autowired
    private UserRepository userRepository;

    /**
     * 添加学生
     * @param userName
     * @param password
     * @return
     */
    @PostMapping(value = "/addUser")
    public TbUser addUser(@RequestParam("userName") String userName, @RequestParam("password") String password){
        TbUser user = new TbUser();
        user.setUesrName(userName);
        user.setPassword(password);

        return userRepository.save( user);
    }

}
