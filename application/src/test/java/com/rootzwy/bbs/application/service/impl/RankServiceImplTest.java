package com.rootzwy.bbs.application.service.impl;

import com.google.gson.Gson;
import com.rootzwy.bbs.common.dto.MultiResponse;
import com.rootzwy.bbs.common.dto.SingleResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RankServiceImplTest {

    @Autowired
    private RankServiceImpl rankService;

    @Test
    public void test() {
        MultiResponse<?> rank = rankService.getRank();
        System.out.println(new Gson().toJson(rank));
    }

}