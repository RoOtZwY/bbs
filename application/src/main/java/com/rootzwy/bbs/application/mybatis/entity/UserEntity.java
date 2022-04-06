package com.rootzwy.bbs.application.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rootzwy.bbs.common.dto.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author zwy
 * @date 2022/2/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("user")
public class UserEntity extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("account")
    private String account;

    @TableField("email")
    private String email;

    @TableField("nickname")
    private String nickname;

    @TableField("gender")
    private Integer gender;

    @TableField("status")
    private Integer status;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("avatar")
    private String avatar;

    @TableField("article_num")
    private Integer articleNum;

}