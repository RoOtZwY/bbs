package com.rootzwy.bbs.admin.service;

import com.rootzwy.bbs.admin.dataobject.JWTDO;
import com.rootzwy.bbs.admin.properties.EnablePrivate;

/**
 * @author zwy
 * @date 2022/2/19
 */
public interface PrivateService {
    String getMD5Key();

    JWTDO getJWTInfo();

    Integer getJWTCookieExpireSeconds();
}
