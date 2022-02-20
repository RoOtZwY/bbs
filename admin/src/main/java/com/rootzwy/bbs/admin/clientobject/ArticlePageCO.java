package com.rootzwy.bbs.admin.clientobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

/**
 * @author zwy
 * @date 2022/2/20
 */
public class ArticlePageCO {

    private Long id;

    private Long userId;

    private String title;

    private String content;

    private Integer viewNum;

    private Integer likeNum;

    private Integer commentNum;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer hided;

}
