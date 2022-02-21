package com.rootzwy.bbs.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rootzwy.bbs.admin.clientobject.UserPageCO;
import com.rootzwy.bbs.admin.dataobject.UserBanDO;
import com.rootzwy.bbs.admin.dataobject.UserPageDO;
import com.rootzwy.bbs.admin.dataobject.UserUnbanDO;
import com.rootzwy.bbs.common.dto.Response;
import com.rootzwy.bbs.common.dto.SingleResponse;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zwy
 * @date 2022/2/21
 */
public interface UserManageService {

    SingleResponse<Page<UserPageCO>> pageUsers(UserPageDO userPageDO);

    @Transactional(rollbackFor = Exception.class)
    Response banUser(UserBanDO userBanDO);

    @Transactional(rollbackFor = Exception.class)
    Response unbanUser(UserUnbanDO userUnbanDO);

}
