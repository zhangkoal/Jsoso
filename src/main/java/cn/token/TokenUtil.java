package cn.token;

import cn.cfg.CacheDataFactory;
import cn.constant.RedisWorkspace;
import cn.util.EmptyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: sfpy
 * @Date: 5/21/2019 9:58 PM
 * @Descirption
 * @Version 1.0
 */
@Component
public class TokenUtil {

    @Autowired
    private CacheDataFactory cacheDataFactory;

    /**
     * 校验token是否有效
     * @param token
     * @return
     */
    public boolean  checkToken(String token) {

        Object value = cacheDataFactory.getCacheDateTime(RedisWorkspace.API_SPACE.getValue() + token);
        if(EmptyUtils.isNotEmpty(value) && !value.toString().equals("-2")) {
            return true;
        }
        return false;

    }
}
