package com.javen.sizuka.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by javen on 2017/12/1.
 */
@Component
public class TokenUtil {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    public Boolean isAuth(String token){
        if(token==null)return false;
        String auth = stringRedisTemplate.opsForValue().get(token);
        if(auth==null) return false;
        return true;
    }
}
