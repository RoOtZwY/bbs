package com.rootzwy.bbs.application.redis.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.rootzwy.bbs.application.redis.StringJsonRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author zwy
 * @date 2022/1/24
 */
@Service
public class DefaultRedisServiceImpl implements DefaultRedisService {

    /**
     * Resource 注解，无论是否指定 type 都会先 byName 注入，所以一般还是会用 Autowired
     */
    @Resource(name = "stringJsonRedisTemplate")
    private StringJsonRedisTemplate redisTemplate;

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(String key, Class<T> targetType) {
        Object value = redisTemplate.opsForValue().get(key);
        if (targetType == String.class) {
            return (T) StrUtil.toStringOrNull(value);
        }
        return BeanUtil.toBean(value, targetType);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getDel(String key, Class<T> targetType) {
        Object value = redisTemplate.opsForValue().getAndDelete(key);
        if (targetType == String.class) {
            return (T) StrUtil.toStringOrNull(value);
        }
        return BeanUtil.toBean(value, targetType);
    }

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void setEx(String key, Object value, TimeUnit timeUnit, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    @Override
    public Boolean setNx(String key, Object value, TimeUnit timeUnit, long timeout) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, timeout, timeUnit);
    }

    @Override
    public Long incr(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    @Override
    public Long sAdd(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    @Override
    public Boolean sIsMember(String key, Object member) {
        return redisTemplate.opsForSet().isMember(key, member);
    }

    @Override
    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public Long zAdd(String key, Set<ZSetOperations.TypedTuple<Object>> typedTuples) {
        return redisTemplate.opsForZSet().add(key, typedTuples);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Set<T> zRevRange(String key, long begin, long end, Class<T> type) {
        Set<Object> objects = redisTemplate.opsForZSet().reverseRange(key, begin, end);
        Set<T> res = new HashSet<>((int) (end - begin));
        if (CollectionUtil.isEmpty(objects)) {
            return res;
        }
        objects.forEach(o -> res.add((T) o));
        return res;
    }

}
