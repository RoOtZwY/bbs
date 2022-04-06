package com.rootzwy.bbs.application.redis;

import cn.hutool.core.util.ObjectUtil;
import com.rootzwy.bbs.application.redis.service.DefaultRedisService;
import com.rootzwy.bbs.common.exception.BusinessException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 *
 * 有两种情况实现的分布式锁。
 * 一种是用于抢占执行权的分布式锁，不用循环等待，比如多个服务见竞争执行一个定时任务。
 * 一种是用于抢占资源（如数据库）的分布式锁，需要循环等待至资源使用完毕后方才解锁。
 * @author zwy
 * @date 2022/2/8
 */
@Deprecated
@Component
public class RedisLock {

    @Resource
    private DefaultRedisService defaultRedisService;

    public void execute(Runnable runnable) {
        runnable.run();
    }

    public Boolean tryLock(String key, Object value, TimeUnit timeUnit, long timeout, long awaitTimeout) {
        long timeoutMillis = timeUnit.toMillis(timeout);
        long awaitTimeoutMillis = timeUnit.toMillis(awaitTimeout);
        long beginTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - beginTime <= awaitTimeoutMillis) {
            Object setValue = ObjectUtil.isNull(value) ? System.currentTimeMillis() + timeoutMillis : value;
            if (defaultRedisService.setNx(key, setValue, timeUnit, timeout)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 分两种情况
     * 一是锁被删除成功了，代表着锁被正常使用。
     * 二是锁删除失败了，代表着 Redis 内部没有这把锁，意味着这把锁被其它操作删除或过期了，则抛出异常
     * @param key 要 del 的 key
     */
    public void unlock(String key) {
        if (!defaultRedisService.del(key)) {
            // 抛出异常
            // 在中间有远程调用，如果没有分布式事务，就会出现回滚失败的问题
            throw new BusinessException("500", "分布式锁解锁出错");
        }
    }

    public <T> T unlock(String key, Class<T> valueType) {
        return defaultRedisService.getDel(key, valueType);
    }

}
