package com.rootzwy.bbs.common.messagecode;

import com.rootzwy.bbs.common.constants.StatusCode;

/**
 * @author zwy
 */
public enum BusinessErrorCode implements ErrorCode {

    DEFAULT(StatusCode.INTERNAL_SERVER_ERROR, "系统异常"),

    NULL_USER(StatusCode.INTERNAL_SERVER_ERROR, "该用户不存在"),
    ERROR_PASSWORD(StatusCode.INTERNAL_SERVER_ERROR, "密码错误"),

    TOKEN_TIMEOUT(StatusCode.INTERNAL_SERVER_ERROR, "身份校验凭证过期，请重新登录"),
    TOKEN_INVALID(StatusCode.INTERNAL_SERVER_ERROR, "身份凭证无效，请登录"),
    ;

    private final String CODE;
    private final String DESCRIPTION;

    BusinessErrorCode(String code, String description) {
        this.CODE = code;
        this.DESCRIPTION = description;
    }

    @Override
    public String getCode() {
        return this.CODE;
    }

    @Override
    public String getDescription() {
        return this.DESCRIPTION;
    }
}
