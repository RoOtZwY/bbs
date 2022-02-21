package com.rootzwy.bbs.admin.enums;

/**
 * @author zwy
 * @date 2022/2/20
 */
public enum ArticleStatusEnum {

    HIDED(1),
    EXPOSED(0);

    private final Integer CODE;

    ArticleStatusEnum(int code) {
        this.CODE = code;
    }

    public Integer getCode() {
        return CODE;
    }
}
