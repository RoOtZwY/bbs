package com.rootzwy.bbs.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rootzwy.bbs.admin.clientobject.ArticlePageCO;
import com.rootzwy.bbs.admin.dataobject.ArticlePageDO;
import com.rootzwy.bbs.admin.dataobject.ArticleRemoveDO;
import com.rootzwy.bbs.admin.dataobject.ArticleStatusDO;
import com.rootzwy.bbs.admin.service.ArticleManageService;
import com.rootzwy.bbs.common.dto.Response;
import com.rootzwy.bbs.common.dto.SingleResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author zwy
 * @date 2022/2/19
 */
@AllArgsConstructor
@RestController
@RequestMapping("/article")
public class ArticleController {

    private ArticleManageService articleManageService;

    @PostMapping("/remove")
    public Response removeArticles(@RequestBody ArticleRemoveDO articleRemoveDO) {
        return articleManageService.removeArticles(articleRemoveDO);
    }

    @PostMapping("/hide")
    public Response changeArticlesStatus(@RequestBody ArticleStatusDO articleStatusDO) {
        return articleManageService.changeArticlesStatus(articleStatusDO);
    }

    @PostMapping("/page")
    public SingleResponse<Page<ArticlePageCO>> pageArticles(@RequestBody ArticlePageDO articlePageDO) {
        return articleManageService.pageArticles(articlePageDO);
    }

}
