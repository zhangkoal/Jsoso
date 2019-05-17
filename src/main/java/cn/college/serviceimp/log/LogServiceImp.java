package cn.college.serviceimp.log;

import cn.college.dao.log.LogDao;
import cn.college.service.log.LogService;
import cn.entity.TbLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: sfpy
 * @Date: 5/14/2019 1:02 PM
 * @Descirption
 * @Version 1.0
 */
@Service
public class LogServiceImp implements LogService{

    @Autowired
    private LogDao logDao;

    @Override
    public void addLoginLog(TbLog log) {

    }
}
