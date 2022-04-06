package com.rootzwy.bbs.application.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rootzwy.bbs.application.mybatis.entity.ArticleEntity;
import com.rootzwy.bbs.application.mybatis.mapper.ArticleMapper;
import com.rootzwy.bbs.application.mybatis.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * @author zwy
 * @date 2022/2/19
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleEntity> implements ArticleService {
}
