package com.example.newdraft.util;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author 吴成卓
 * @version V1.0
 * @Project: demo_redis_token
 * @Package com.example.demo_redis_token.util
 * @Description:
 * @date 2020/1/5 星期日 13:46
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    @Bean
    public RedisTemplate<String,Object>redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Object>redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }
}
