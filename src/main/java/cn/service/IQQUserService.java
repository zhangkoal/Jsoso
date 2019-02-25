package cn.service;

import cn.domain.LoginUserInfo;
import cn.domain.TbUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IQQUserService extends JpaRepository<LoginUserInfo, Integer> {

    @Query("SELECT count(*) FROM LoginUserInfo t  WHERE t.qqUserId=?1")
   public int getCountByUserId(@Param("qqUserId")String qqUserId);

    @Query(value = "SELECT * from login_user_info t where t.qq_user_id =?1", nativeQuery = true)
    public List<LoginUserInfo> getUserInfo(@Param("qqUserId") String qqUserId);
}
