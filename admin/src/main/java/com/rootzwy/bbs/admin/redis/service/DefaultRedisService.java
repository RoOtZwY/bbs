package com.rootzwy.bbs.admin.redis.service;

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


}
