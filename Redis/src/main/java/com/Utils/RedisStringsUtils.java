package com.Utils;

import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: demo
 * @description: Strings 相关命令工具
 * @author: Mr.lgj
 * @version:
 * @See:
 * @create: 2018-08-28 02:40
 **/
public class RedisStringsUtils<K,V> {

    RedisTemplate<K, V> redisTemplate;

    /**
     * @description:  构造函数
     * @param:   RedisTemplate<String, Object> redisTemplate   模板
     * @return:
     * @author: Mr.lgj
     * @date: 7/2/18
     */
    public RedisStringsUtils(RedisTemplate<K, V> redisTemplate) {

        this.redisTemplate = redisTemplate;
    }


    /**
     * @description:  set 设置
     * @param:  　　key　键
     * 　　　　　　　value 值
     * @return:  　true  成功
     * 　　　　　　　false　失败
     * @author: Mr.lgj
     * @date: 8/28/18
    */
    public boolean set(K key,V value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    /**
     * @description:  set 设置 ,并且设置过期时间
     * @param:  　　key　键
     * 　　　　　　　value 值
     * @return:  　true  成功
     * 　　　　　　　false　失败
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    public boolean set(K key,V value,long var3, TimeUnit var5) {
        try {
            redisTemplate.opsForValue().set(key, value, var3,  var5);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * @description: 同时设置多个key
     * @param:       var : the map of the data
     * @return:      true : set success ; false : set fail
     * @author: Mr.lgj
     * @date: 8/28/18
    */
    public boolean multiSet(Map<? extends K, ? extends V> var){
        try {
            redisTemplate.opsForValue().multiSet(var);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @description: 同时设置多个key,,只有不存在任何一个键时，所有的键才能设置成功
     * @param:       var : the map of the data
     * @return:      true : set success ; false : set fail
     * @author: Mr.lgj
     * @date: 8/28/18
     */
    public  boolean multiSetIfAbsent(Map<? extends K, ? extends V> var){
        try {
            redisTemplate.opsForValue().multiSetIfAbsent(var);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public  Object get(K key){
        try {
             redisTemplate.opsForValue().get(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


     /*
    @Nullable
    V getAndSet(K var1, V var2);

    @Nullable
    List<V> multiGet(Collection<K> var1);

    @Nullable
    Long increment(K var1, long var2);

    @Nullable
    Double increment(K var1, double var2);

    @Nullable
    Integer append(K var1, String var2);

    @Nullable
    String get(K var1, long var2, long var4);

    void set(K var1, V var2, long var3);

    @Nullable
    Long size(K var1);

    @Nullable
    Boolean setBit(K var1, long var2, boolean var4);

    @Nullable
    Boolean getBit(K var1, long var2);

    RedisOperations<K, V> getOperations();

   */



}
