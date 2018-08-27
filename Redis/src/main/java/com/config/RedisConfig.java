package com.config;


import com.Utils.RedisListUtil;
import com.Utils.RedisStringsUtils;
import com.Utils.RedisUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @program:  Redis
 * @description: Redis配置
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-07-02 01:46
 **/
@Configuration
public class RedisConfig {

    @Value("${spring.redis.jedis.pool.max-active}")
    private Integer maxActive;
    //最大空闲处
    @Value("${spring.redis.jedis.pool.max-idle}")
    private Integer maxIdle;
    //最大等待时间
    @Value("${spring.redis.jedis.pool.max-wait}")
    private String maxWait;
    //连接池中的最小空闲连接
    @Value("${spring.redis.jedis.pool.min-idle}")
    private Integer minIdle;


    //host name
    @Value("${spring.redis.host}")
    private String host;
    //连接池中的最小空闲连接
    @Value("${spring.redis.port}")
    private int port;


    /**
     * @description:   创建  JedisPoolConfig Bean
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 7/2/18
    */
    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        System.out.println(this.toString());
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);

        return jedisPoolConfig;

    }
    /**
     * @description: 创建 JedisConnectionFactory Bean
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 7/2/18
    */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(port);
        return jedisConnectionFactory;

    }
    /**
     * @description: 创建  RedisTemplate Bean
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 7/2/18
    */
    @Bean
    public RedisTemplate<String, Object> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }
    /**
     * @description:  配置模板
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 7/2/18
    */
    private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
        //如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
        //配置key序列化机制
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //配置value序列化机制，实体类必须有set方法
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(factory);
    }

    /**
     * @description: 创建 RedisUtils  Bean
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 7/2/18
    */
    @Bean
    public RedisUtils  redisUtils(RedisTemplate<String, Object> redisTemplate){
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
    public RedisStringsUtils redisStringsUtils(RedisTemplate<String, Object> redisTemplate){
        System.out.println("RedisStringsUtils bean 创建.............");
        RedisStringsUtils redisStringsUtils = new RedisStringsUtils(redisTemplate);
        return  redisStringsUtils;
    }
    /**
     * @description: list 操作
     * @param:
     * @return:
     * @author: Mr.lgj
     * @date: 8/28/18
    */
    public RedisListUtil redisListUtils(RedisTemplate<String, Object> redisTemplate){
        System.out.println("RedisStringsUtils bean 创建.............");
        RedisListUtil redisListUtil = new RedisListUtil(redisTemplate);

        return  redisListUtil;
    }
    @Override
    public String toString() {
        return "RedisConfig{" +
                "maxActive=" + maxActive +
                ", maxIdle=" + maxIdle +
                ", maxWait=" + maxWait +
                ", minIdle=" + minIdle +
                '}';
    }
}
