package com.rootzwy.bbs.application.service;

import com.rootzwy.bbs.application.mybatis.entity.ArticleEntity;
import com.rootzwy.bbs.common.dto.MultiResponse;

/**
 * @author zwy
 * @date 2022/2/21
 */
public interface RankService {

    MultiResponse<?> getRank();

    Long calculateArticleRankScore(ArticleEntity articleEntity);

}
