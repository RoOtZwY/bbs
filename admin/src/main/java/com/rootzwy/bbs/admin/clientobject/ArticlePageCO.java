package com.rootzwy.bbs.admin.clientobject;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zwy
 * @date 2022/2/20
 */
@Data
public class ArticlePageCO {

    private Long id;

    private Long userId;

    private String title;

    private Integer viewNum;

    private Integer likeNum;

    private Integer commentNum;

    private Integer hided;

}
