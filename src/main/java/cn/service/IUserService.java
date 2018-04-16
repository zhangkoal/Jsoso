package cn.service;

import cn.domain.TbUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserService extends JpaRepository<TbUser, Integer> {

    @Query("SELECT count(*) FROM TbUser t  WHERE t.uesrName=?1")
   public int getCountByUserName(@Param("username") String userName);

    @Query("SELECT t.userId FROM TbUser t  WHERE t.uesrName=?1 and t.password=?2")
    public Object userLogin(@Param("username") String userName, @Param("password") String password);
}
