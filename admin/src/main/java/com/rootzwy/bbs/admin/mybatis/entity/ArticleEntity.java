package com.rootzwy.bbs.admin.mybatis.entity;

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
 * @date 2022/2/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("article")
public class ArticleEntity extends BaseEntity {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("title")
    private String title;

    @TableField("content")
    private String content;

    @TableField("view_num")
    private Integer viewNum;

    @TableField("like_num")
    private Integer likeNum;

    @TableField("comment_num")
    private Integer commentNum;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("hided")
    private Integer hided;

}
