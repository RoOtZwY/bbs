package com.rootzwy.bbs.application.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * @author zwy
 * @date 2022/1/20
 */
@Configuration
public class RedisConfiguration {

    @Bean
    public StringJsonRedisTemplate stringJsonRedisTemplate(RedisConnectionFactory redisConnectionFactory,
                                                           ObjectMapper objectMapper) {
        return new StringJsonRedisTemplate(redisConnectionFactory, objectMapper);
    }

}
