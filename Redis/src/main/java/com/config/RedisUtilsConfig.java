package com.config;

import com.Utils.RedisListUtil;
import com.Utils.RedisSetUtil;
import com.Utils.RedisStringsUtil;
import com.Utils.RedisUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @program: demo
 * @description:
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-08-28 16:42
 **/

@Configuration
public class RedisUtilsConfig {

    /**
     * @description: 创建 RedisUtils  Bean
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 7/2/18
     */
    @Bean
    public RedisUtils redisUtils(RedisTemplate<String, Object> redisTemplate){
        RedisUtils redisUtils = new RedisUtils(redisTemplate);
        return  redisUtils;
    }

    /**
     * @description:   String
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Bean
    public RedisStringsUtil redisStringsUtil(RedisTemplate<String, Object> redisTemplate){
        RedisStringsUtil redisStringsUtils = new RedisStringsUtil(redisTemplate);
        return  redisStringsUtils;
    }
    /**
     * @description: list 操作
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Bean
    public  RedisListUtil<String,String> redisListUtils(RedisTemplate<String,String> redisTemplate){
        RedisListUtil<String,String> redisListUtil = new RedisListUtil<String,String>(redisTemplate);

        return  redisListUtil;
    }

    /**
     * @description: set 操作
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    @Bean
    public RedisSetUtil redisSetUtil(RedisTemplate<String, Object> redisTemplate){
        RedisSetUtil redisSetUtil = new RedisSetUtil(redisTemplate);

        return  redisSetUtil;
    }



}
