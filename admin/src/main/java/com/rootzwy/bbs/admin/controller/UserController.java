package com.rootzwy.bbs.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rootzwy.bbs.admin.clientobject.UserPageCO;
import com.rootzwy.bbs.admin.dataobject.UserBanDO;
import com.rootzwy.bbs.admin.dataobject.UserPageDO;
import com.rootzwy.bbs.admin.dataobject.UserUnbanDO;
import com.rootzwy.bbs.admin.service.UserManageService;
import com.rootzwy.bbs.common.dto.Response;
import com.rootzwy.bbs.common.dto.SingleResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zwy
 * @date 2022/2/21
 */
@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private UserManageService userManageService;

    @PostMapping("/page")
    public SingleResponse<Page<UserPageCO>> pageUsers(@RequestBody UserPageDO userPageDO) {
        return userManageService.pageUsers(userPageDO);
    }

    @PostMapping("/ban")
    public Response banUser(@RequestBody UserBanDO userBanDO) {
        return userManageService.banUser(userBanDO);
    }

    @PostMapping("/unban")
    public Response unbanUser(UserUnbanDO userUnbanDO) {
        return userManageService.unbanUser(userUnbanDO);
    }

}
