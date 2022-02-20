package com.rootzwy.bbs.admin.controller;

import com.rootzwy.bbs.admin.annotation.IgnoreAdminToken;
import com.rootzwy.bbs.admin.annotation.LoginAdmin;
import com.rootzwy.bbs.admin.clientobject.AdminCO;
import com.rootzwy.bbs.admin.context.AdminContext;
import com.rootzwy.bbs.admin.dataobject.AdminLoginDO;
import com.rootzwy.bbs.admin.service.AdminHandleService;
import com.rootzwy.bbs.common.dto.Response;
import com.rootzwy.bbs.common.dto.SingleResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zwy
 * @date 2022/2/19
 */
@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class AdminController {

    private AdminHandleService adminHandleService;

    @PostMapping("/login")
    @IgnoreAdminToken
    public Response login(@RequestBody AdminLoginDO adminLoginDO, HttpServletResponse response) {
        return adminHandleService.login(adminLoginDO, response);
    }

    @PostMapping("/info")
    public SingleResponse<AdminCO> getAdminInfo(@LoginAdmin AdminContext adminContext) {
        return adminHandleService.getAdminInfo(adminContext);
    }

}
