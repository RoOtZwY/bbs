package com.rootzwy.bbs.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rootzwy.bbs.admin.clientobject.ArticlePageCO;
import com.rootzwy.bbs.admin.dataobject.ArticleHideDO;
import com.rootzwy.bbs.admin.dataobject.ArticlePageDO;
import com.rootzwy.bbs.admin.dataobject.ArticleRemoveDO;
import com.rootzwy.bbs.admin.enums.HidedEnum;
import com.rootzwy.bbs.admin.mybatis.entity.ArticleEntity;
import com.rootzwy.bbs.admin.mybatis.service.ArticleService;
import com.rootzwy.bbs.admin.service.ArticleManageService;
import com.rootzwy.bbs.admin.util.PageUtils;
import com.rootzwy.bbs.common.dto.Response;
import com.rootzwy.bbs.common.dto.SingleResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zwy
 * @date 2022/2/20
 */
@AllArgsConstructor
@Service
public class ArticleManageServiceImpl implements ArticleManageService {

    private ArticleService articleService;

    @Override
    public Response removeArticles(ArticleRemoveDO articleRemoveDO) {
        articleService.removeBatchByIds(articleRemoveDO.getIdList());
        return Response.buildSuccess();
    }

    @Override
    public Response hideArticles(ArticleHideDO articleHideDO) {
        List<ArticleEntity> articleEntities = articleService.listByIds(articleHideDO.getIdList());
        articleEntities.forEach(entity -> {
            if (HidedEnum.EXPOSED.getCode().equals(entity.getHided())) {
                entity.setHided(HidedEnum.HIDED.getCode());
            }
        });
        articleService.updateBatchById(articleEntities);
        return Response.buildSuccess();
    }

    @Override
    public SingleResponse<Page<ArticlePageCO>> pageArticles(ArticlePageDO articlePageDO) {
        Page<ArticleEntity> articleEntityPage = new Page<>();
        articleEntityPage.setCurrent(articlePageDO.getCurrent());
        articleEntityPage.setSize(articlePageDO.getSize());
        Page<ArticleEntity> page = articleService.page(articleEntityPage);
        Page<ArticlePageCO> articlePageCOPage = PageUtils.convertPage(page, ArticlePageCO.class);
        return SingleResponse.of(articlePageCOPage);
    }

}
