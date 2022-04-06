package com.rootzwy.bbs.application.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.connection.DefaultStringRedisConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 自定义 RedisTemplate
 * @author zwy
 * @date 2022/1/20
 */
public class StringJsonRedisTemplate extends RedisTemplate<String, Object> {

    public StringJsonRedisTemplate(ObjectMapper objectMapper) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer =
                new Jackson2JsonRedisSerializer<>(Object.class);
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        this.setKeySerializer(stringRedisSerializer);
        this.setValueSerializer(jackson2JsonRedisSerializer);
        this.setHashKeySerializer(stringRedisSerializer);
        this.setHashValueSerializer(jackson2JsonRedisSerializer);
    }

    public StringJsonRedisTemplate(RedisConnectionFactory redisConnectionFactory,
                                   ObjectMapper objectMapper) {
        this(objectMapper);
        this.setConnectionFactory(redisConnectionFactory);
        this.afterPropertiesSet();
    }
    
    @Override
    protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
        return new DefaultStringRedisConnection(connection);
    }

}
