package com.rootzwy.bbs.admin.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rootzwy.bbs.common.dto.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zwy
 * @date 2022/2/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("admin")
public class AdminEntity extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("account")
    private String account;

    @TableField("password")
    private String password;

    @TableField("avatar")
    private String avatar;

    @TableField("role")
    private Integer role;

}
