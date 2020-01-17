package com.example.newdraft.util;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: demo_redis_token
 * @Package com.example.demo_redis_token.util
 * @Description:
 * @date 2020/1/5 星期日 13:49
 */
@Component
public class RedisUtils {
    @Resource
    private RedisTemplate <String,Object> redisTemplate;

    /**
     * 获取
     * @param key
     * @return
     */
    public Object get(String key){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations<String, Object> vo =redisTemplate.opsForValue();
       return vo.get(key);
    }

    public boolean judgeToken(String key){
        Object o = get(key);
        if(o==null||o==""){
            return false;
        }
        return true;
    }



    /**
     * 删除
     * @param key
     * @return
     */
    public boolean delete(String key){
        try{
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(new StringRedisSerializer());
            redisTemplate.delete(key);
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
    }


    public boolean expire(final String key,final long time){
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                boolean flag =false;
                try {
                    redisTemplate.setKeySerializer(new StringRedisSerializer());
                    redisTemplate.setValueSerializer(new StringRedisSerializer());
                    byte[] keys = new StringRedisSerializer().serialize(key);
                    flag= redisConnection.expire(keys,time);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return flag;
            }
        });
    }

    /**
     * 添加缓存设置超时时间
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean set(String key,String value,long time){
        try {
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(new StringRedisSerializer());
            ValueOperations<String, Object> vo = redisTemplate.opsForValue();
            vo.set(key,value);
            expire(key,time);
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 添加缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key,String value){
        try {
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(new StringRedisSerializer());
            ValueOperations<String, Object> vo = redisTemplate.opsForValue();
            vo.set(key,value);
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
