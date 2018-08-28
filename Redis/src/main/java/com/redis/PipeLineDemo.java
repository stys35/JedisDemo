package com.redis;

import com.Utils.RedisStringsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Pipeline;

/**
 * @program: demo
 * @description: PipeLine 使用
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-08-29 02:18
 **/
@Component
public class PipeLineDemo  implements SessionCallback<Object>  {

    private static final Logger log  =  LoggerFactory.getLogger("StringsDemo");


    @Autowired
    private RedisStringsUtil redisStringsUtil;

    @Autowired
    // @Qualifier()
    RedisTemplate<String,Object> redisTemplate;

    public void pipeLineSet(){

        Long time = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            redisTemplate.opsForValue().increment("pipline", 1);
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> redisOperations) throws DataAccessException {
                for (int i = 0; i < 10000; i++) {
                    redisTemplate.opsForValue().increment("pipline", 1L);
                }
                return null;
            }
        });
        System.out.println("耗时：" + (System.currentTimeMillis() - time));
    }


    public void pipeLineTest(){

        Long time = System.currentTimeMillis();
        redisTemplate.executePipelined(this);
        System.out.println("耗时：" + (System.currentTimeMillis() - time));
    }


    @Override
    public <K, V> Object execute(RedisOperations<K, V> redisOperations) throws DataAccessException {
        for (int i = 0; i < 10000; i++) {

            redisTemplate.opsForValue().increment("pipline-data", 1L);
        }

        return null;
    }


}

