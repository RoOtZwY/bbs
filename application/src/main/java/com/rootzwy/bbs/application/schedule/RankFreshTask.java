package com.rootzwy.bbs.application.schedule;

import com.rootzwy.bbs.application.service.RankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zwy
 * @date 2022/3/16
 */
@Component
@Slf4j
public class RankFreshTask {

    @Resource
    private RankService rankService;

    @Scheduled(cron = "0 0/5 * * * ?")
    public void freshRank() {
        log.info("开始执行排行榜刷新定时任务");
    }

}
