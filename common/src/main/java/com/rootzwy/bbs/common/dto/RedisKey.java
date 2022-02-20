package com.rootzwy.bbs.common.dto;

/**
 * Redis 键格式规范定义类
 * @author zwy
 * @date 2022/1/26
 */
public abstract class RedisKey extends DTO {

    private String prefix;

    public RedisKey(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

}
