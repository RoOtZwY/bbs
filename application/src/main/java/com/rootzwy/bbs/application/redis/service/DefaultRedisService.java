package com.rootzwy.bbs.application.redis.service;

import org.springframework.data.redis.core.ZSetOperations;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author zwy
 * @date 2022/1/24
 */
public interface DefaultRedisService {

    <T> T get(String key, Class<T> type);

    <T> T getDel(String key, Class<T> targetType);

    void set(String key, Object value);

    void setEx(String key, Object value, TimeUnit timeUnit, long timeout);

    Boolean setNx(String key, Object value, TimeUnit timeUnit, long timeout);

    Long incr(String key);

    Long sAdd(String key, Object... values);

    Boolean sIsMember(String key, Object member);

    Boolean del(String key);

    Long zAdd(String key, Set<ZSetOperations.TypedTuple<Object>> typedTuples);

    <T> Set<T> zRevRange(String key, long begin, long end, Class<T> type);
}
