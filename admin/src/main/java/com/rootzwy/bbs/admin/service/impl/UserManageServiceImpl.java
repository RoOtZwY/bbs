package com.rootzwy.bbs.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rootzwy.bbs.admin.clientobject.UserPageCO;
import com.rootzwy.bbs.admin.dataobject.UserBanDO;
import com.rootzwy.bbs.admin.dataobject.UserPageDO;
import com.rootzwy.bbs.admin.dataobject.UserUnbanDO;
import com.rootzwy.bbs.admin.enums.UserBanEnum;
import com.rootzwy.bbs.admin.mybatis.entity.UserEntity;
import com.rootzwy.bbs.admin.mybatis.service.UserService;
import com.rootzwy.bbs.admin.redis.keys.UserBanKey;
import com.rootzwy.bbs.admin.redis.service.DefaultRedisService;
import com.rootzwy.bbs.admin.service.UserManageService;
import com.rootzwy.bbs.admin.util.PageUtils;
import com.rootzwy.bbs.common.dto.Response;
import com.rootzwy.bbs.common.dto.SingleResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @author zwy
 * @date 2022/2/21
 */
@AllArgsConstructor
@Service
public class UserManageServiceImpl implements UserManageService {

    private UserService userService;

    private DefaultRedisService defaultRedisService;

    @Override
    public SingleResponse<Page<UserPageCO>> pageUsers(UserPageDO userPageDO) {
        Page<UserEntity> userEntityPage = new Page<>();
        userEntityPage.setCurrent(userPageDO.getCurrent());
        userEntityPage.setSize(userPageDO.getSize());
        Page<UserEntity> page = userService.page(userEntityPage);
        Page<UserPageCO> userPageCOPage = PageUtils.convertPage(page, UserPageCO.class);
        return SingleResponse.of(userPageCOPage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response banUser(UserBanDO userBanDO) {
        boolean success = userService.update(new LambdaUpdateWrapper<UserEntity>()
                .set(UserEntity::getStatus, UserBanEnum.BANED.getCode())
                .eq(UserEntity::getId, userBanDO.getId()));
        if (success) {
            long timestamp = System.currentTimeMillis() + TimeUnit.DAYS.toMillis(userBanDO.getBanDay());
            defaultRedisService.setEx(UserBanKey.getUserBanKey(userBanDO.getId()),
                    timestamp, TimeUnit.DAYS, userBanDO.getBanDay());
        }
        return Response.buildSuccess();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response unbanUser(UserUnbanDO userUnbanDO) {
        boolean success = userService.update(new LambdaUpdateWrapper<UserEntity>()
                .set(UserEntity::getStatus, UserBanEnum.NORMAL.getCode())
                .eq(UserEntity::getId, userUnbanDO.getId()));
        if (success) {
            defaultRedisService.del(UserBanKey.getUserBanKey(userUnbanDO.getId()));
        }
        return Response.buildSuccess();
    }

}
