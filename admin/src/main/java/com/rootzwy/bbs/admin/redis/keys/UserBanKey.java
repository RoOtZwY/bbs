package com.rootzwy.bbs.admin.redis.keys;

import com.rootzwy.bbs.common.dto.DatabaseRedisKey;

/**
 * @author zwy
 * @date 2022/2/21
 */
public class UserBanKey extends DatabaseRedisKey {

    public UserBanKey(String prefix) {
        super("bbs", "user", prefix);
    }

    public static String getUserBanKey(long id) {
        return new UserBanKey("ban:id:" + id).getPrefix();
    }

}