package cn.college.service.log;

import cn.entity.TbLog;
import org.springframework.stereotype.Service;

/**
 * @Author: sfpy
 * @Date: 5/14/2019 1:01 PM
 * @Descirption
 * @Version 1.0
 */
@Service
public interface LogService{
    /**
     * 登录日志添加
     */
    void addLoginLog(TbLog log);
}
