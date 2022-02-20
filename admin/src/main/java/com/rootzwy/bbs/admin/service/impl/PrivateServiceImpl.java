package com.rootzwy.bbs.admin.service.impl;

import com.rootzwy.bbs.admin.dataobject.JWTDO;
import com.rootzwy.bbs.admin.properties.EnablePrivate;
import com.rootzwy.bbs.admin.properties.PrivateInformationProperties;
import com.rootzwy.bbs.admin.service.PrivateService;

/**
 * 仅仅为了快速开发而直接注入 properties 到一个 service
 * @author zwy
 * @date 2022/2/19
 */
public class PrivateServiceImpl implements PrivateService, EnablePrivate {

    private PrivateInformationProperties privateInformationProperties;

    private static final int JWT_EXPIRE_DATE = 5;

    private static final Integer JWT_COOKIE_EXPIRE_SECONDS = JWT_EXPIRE_DATE * 24 * 60 * 60;

    @Override
    public void setProperties(PrivateInformationProperties properties) {
        this.privateInformationProperties = properties;
    }

    @Override
    public String getMD5Key() {
        return privateInformationProperties.getMd5Key();
    }

    @Override
    public JWTDO getJWTInfo() {
        return new JWTDO(privateInformationProperties.getJwtKey(), JWT_EXPIRE_DATE);
    }

    @Override
    public Integer getJWTCookieExpireSeconds() {
        return JWT_COOKIE_EXPIRE_SECONDS;
    }

}
