package com.xiaohu.websocketim.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author xiaohu
 * @version 1.0
 * @date 2021/4/15 10:24
 */

@Component
public class RedisUtils {

    @Autowired
    public  RedisTemplate<String,String> redisTemplate;


    public  boolean set(String key ,String value) {
        try{
            redisTemplate.opsForValue().set(key,value);
        }catch (Exception e ){
            return false;
        }
        return true;
    }

    public String get (String key) {

        String value = redisTemplate.opsForValue().get(key);

        return value;
    }





}
