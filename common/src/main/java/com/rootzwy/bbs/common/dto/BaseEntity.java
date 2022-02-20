package com.rootzwy.bbs.common.dto;

/**
 * @author zwy
 * @date 2022/1/24
 */
public abstract class BaseEntity extends DTO {

    private Integer deleted;

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

}
