package com.rootzwy.bbs.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rootzwy.bbs.application.dataobjects.ArticleRankDO;
import com.rootzwy.bbs.application.dataobjects.ArticleRankInfoDO;
import com.rootzwy.bbs.application.mybatis.entity.ArticleEntity;
import com.rootzwy.bbs.application.mybatis.service.ArticleService;
import com.rootzwy.bbs.application.redis.keys.ArticleRankRedisKey;
import com.rootzwy.bbs.application.redis.service.DefaultRedisService;
import com.rootzwy.bbs.application.service.RankService;
import com.rootzwy.bbs.common.dto.MultiResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 实时帖子热度排行榜服务
 * @author zwy
 * @date 2022/2/21
 */
@AllArgsConstructor
@Service
public class RankServiceImpl implements RankService, InitializingBean {

    private static final Long RANK_NUM = 50L;

    private DefaultRedisService defaultRedisService;

    private ArticleService articleService;

    @Override
    public MultiResponse<?> getRank() {
        Set<ArticleRankInfoDO> redisRank = defaultRedisService
                .zRevRange(ArticleRankRedisKey.getRankKey(), 0L, RANK_NUM, ArticleRankInfoDO.class);
        // TODO DO to CO
        return MultiResponse.ofWithCollectionSize(new ArrayList<>(redisRank));
    }

    @Override
    public Long calculateArticleRankScore(ArticleEntity articleEntity) {
        Integer likeNum = articleEntity.getLikeNum();
        Integer commentNum = articleEntity.getCommentNum();
        Integer viewNum = articleEntity.getViewNum();
        return likeNum * 2L + commentNum * 3L + viewNum;
    }

    private void refreshRank(Collection<ArticleRankInfoDO> articles) {
        Set<ZSetOperations.TypedTuple<Object>> rankSet = new HashSet<>();
        articles.forEach(rankInfoDO -> {
            ArticleRankDO rankDO = new ArticleRankDO();
            ZSetOperations.TypedTuple<Object> element
                    = ZSetOperations.TypedTuple.of(rankDO, Double.valueOf(rankInfoDO.getScore()));
            rankSet.add(element);
        });
        defaultRedisService.zAdd(ArticleRankRedisKey.getRankKey(), rankSet);
    }

    /**
     * 系统启动时初始化排行榜
     */
    @Override
    public void afterPropertiesSet() {
        List<ArticleEntity> list = articleService.list(new LambdaQueryWrapper<ArticleEntity>()
                .orderByDesc(ArticleEntity::getCommentNum)
                .orderByDesc(ArticleEntity::getLikeNum)
                .orderByDesc(ArticleEntity::getViewNum).last(" LIMIT " + RANK_NUM));
        ArrayList<ArticleRankInfoDO> articleRankInfoDOS = new ArrayList<>();
        list.forEach(articleEntity -> {
            ArticleRankInfoDO articleRankInfoDO = new ArticleRankInfoDO();
            articleRankInfoDO.setId(articleEntity.getId());
            articleRankInfoDO.setTitle(articleRankInfoDO.getTitle());
            Long rankScore = calculateArticleRankScore(articleEntity);
            articleRankInfoDO.setScore(rankScore);
            articleRankInfoDOS.add(articleRankInfoDO);
        });
        refreshRank(articleRankInfoDOS);
    }
}
