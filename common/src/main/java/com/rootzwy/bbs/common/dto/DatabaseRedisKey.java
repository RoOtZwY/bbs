package com.rootzwy.bbs.common.dto;

/**
 * 基于数据库的 Redis 键生成类
 * @author zwy
 * @date 2022/1/26
 */
public abstract class DatabaseRedisKey extends RedisKey {

    public DatabaseRedisKey(String database, String table, String prefix) {
        super(database + ":" + table + ":" + prefix);
    }

}
