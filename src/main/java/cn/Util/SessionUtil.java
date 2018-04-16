package cn.Util;

import cn.cfg.CacheDataFactory;
import cn.constant.RedisType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 对于用户的每一次请求更新用的session时间
 */
@Component
public class SessionUtil {

    @Autowired
    private CacheDataFactory cacheDataFactory;


    /**
     * 获取用户的邮箱验证码
     * @param userName
     * @return
     */
    public Object getuserRegistSeesionValue(String userName){
        String key = RedisType.userRegistSession + userName;
       return cacheDataFactory.getCacheDataByKey(key);
    }
    /**
     * 用户注册session添加
     */
    public void insertUserRegistSession(String userName, String emailCode) throws Exception {
        String key = RedisType.userRegistSession + userName;
        //半小时内生效
        int seconds = 60 * 30;
        cacheDataFactory.updateCacheData(key, emailCode, seconds);
    }

    /**
     * 用户登陆状态session添加
     */
    public void insertUserSession(String userName, String UUID) throws Exception {
        String key = RedisType.userSession + userName;
        //半小时内生效
        int seconds = 60;
        cacheDataFactory.updateCacheData(key, UUID, seconds);
    }
}
