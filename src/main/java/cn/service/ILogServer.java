package cn.service;

import cn.domain.TbLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ILogServer extends JpaRepository<TbLog, Integer> {

    @Query(value = "SELECT '*' FROM TbLog t  ORDER BY loginTime")
    public int getAllLog();
}
