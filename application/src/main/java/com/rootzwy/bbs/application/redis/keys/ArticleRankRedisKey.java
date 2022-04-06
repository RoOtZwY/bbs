package com.rootzwy.bbs.application.redis.keys;

import com.rootzwy.bbs.common.dto.RedisKey;

/**
 * @author zwy
 * @date 2022/3/16
 */
public class ArticleRankRedisKey extends RedisKey {

    public ArticleRankRedisKey(String prefix) {
        super(prefix);
    }

    public static String getRankKey() {
        return new ArticleRankRedisKey("rank").getPrefix();
    }

}
