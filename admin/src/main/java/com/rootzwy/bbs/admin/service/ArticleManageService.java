package com.rootzwy.bbs.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rootzwy.bbs.admin.clientobject.ArticlePageCO;
import com.rootzwy.bbs.admin.dataobject.ArticlePageDO;
import com.rootzwy.bbs.admin.dataobject.ArticleRemoveDO;
import com.rootzwy.bbs.admin.dataobject.ArticleStatusDO;
import com.rootzwy.bbs.common.dto.Response;
import com.rootzwy.bbs.common.dto.SingleResponse;

/**
 * @author zwy
 * @date 2022/2/20
 */
public interface ArticleManageService {

    Response removeArticles(ArticleRemoveDO articleRemoveDO);

    Response changeArticlesStatus(ArticleStatusDO articleStatusDO);

    SingleResponse<Page<ArticlePageCO>> pageArticles(ArticlePageDO articlePageDO);
}
