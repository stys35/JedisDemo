package com.Utils;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @program: demo
 * @description: redis hash  操作
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-08-28 15:57
 **/
public class RedisHashUtil<H, HK, HV>{

    RedisTemplate<H, HV> redisTemplate;
    HashOperations<H, HK, HV> hashOperations;

    /**
     * @description:  构造函数
     * @param:   RedisTemplate<String, Object> redisTemplate   模板
     * @return:
     * @author: Mr.lgj
     * @date: 7/2/18
     */
    public RedisHashUtil(RedisTemplate<H, HV> redisTemplate) {

        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

}
