package com.rootzwy.bbs.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rootzwy.bbs.admin.clientobject.AdminCO;
import com.rootzwy.bbs.admin.constant.AdminConstant;
import com.rootzwy.bbs.admin.context.AdminContext;
import com.rootzwy.bbs.admin.dataobject.AdminLoginDO;
import com.rootzwy.bbs.admin.dataobject.JWTDO;
import com.rootzwy.bbs.admin.mybatis.entity.AdminEntity;
import com.rootzwy.bbs.admin.mybatis.service.AdminService;
import com.rootzwy.bbs.admin.service.AdminHandleService;
import com.rootzwy.bbs.admin.service.PrivateService;
import com.rootzwy.bbs.admin.util.JWTUtil;
import com.rootzwy.bbs.admin.util.MD5Util;
import com.rootzwy.bbs.common.dto.Response;
import com.rootzwy.bbs.common.dto.SingleResponse;
import com.rootzwy.bbs.common.exception.BusinessException;
import com.rootzwy.bbs.common.messagecode.BusinessErrorCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zwy
 * @date 2022/2/19
 */
@Slf4j
@AllArgsConstructor
@Service
public class AdminHandleServiceImpl implements AdminHandleService {

    private PrivateService privateService;

    private AdminService adminService;

    @Override
    public Response login(AdminLoginDO adminLoginDO, HttpServletResponse response) {
        String account = adminLoginDO.getAccount();
        String password = adminLoginDO.getPassword();
        String passwordAfterMD5 = MD5Util.md5(password, privateService.getMD5Key());
        AdminEntity adminEntity = adminService.getOne(new LambdaQueryWrapper<AdminEntity>()
                .eq(AdminEntity::getAccount, account));
        if (ObjectUtil.isNull(adminEntity)) {
            throw new BusinessException(BusinessErrorCode.NULL_USER);
        }
        if (!passwordAfterMD5.equals(adminEntity.getPassword())) {
            throw new BusinessException(BusinessErrorCode.ERROR_PASSWORD);
        }
        try {
            AdminContext adminContext = BeanUtil.copyProperties(adminEntity, AdminContext.class);
            JWTDO jwtInfo = privateService.getJWTInfo();
            String token = JWTUtil.handler(jwtInfo.getKey())
                    .createTokenByObject(adminContext, jwtInfo.getDate());
            Cookie cookie = new Cookie(AdminConstant.ADMIN_TOKEN, token);
            cookie.setMaxAge(privateService.getJWTCookieExpireSeconds());
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
        } catch (Exception e) {
            throw new BusinessException(BusinessErrorCode.DEFAULT, e);
        }
        return Response.buildSuccess();
    }

    @Override
    public SingleResponse<AdminCO> getAdminInfo(AdminContext adminContext) {
        AdminEntity adminEntity = adminService.getById(adminContext.getId());
        return SingleResponse.of(BeanUtil.copyProperties(adminEntity, AdminCO.class));
    }

}
