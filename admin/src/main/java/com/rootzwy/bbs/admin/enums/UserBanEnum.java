package com.rootzwy.bbs.admin.enums;

/**
 * @author zwy
 * @date 2022/2/21
 */
public enum UserBanEnum {

    BANED(2),
    NORMAL(1),
    INACTIVE(0),
    ;

    private final Integer CODE;

    UserBanEnum(int code) {
        this.CODE = code;
    }

    public Integer getCode() {
        return CODE;
    }

}
