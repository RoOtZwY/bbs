package com.rootzwy.bbs.admin.config;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.rootzwy.bbs.admin.annotation.IgnoreAdminToken;
import com.rootzwy.bbs.admin.constant.AdminConstant;
import com.rootzwy.bbs.admin.context.AdminContext;
import com.rootzwy.bbs.admin.context.AdminHolder;
import com.rootzwy.bbs.admin.dataobject.JWTDO;
import com.rootzwy.bbs.admin.service.PrivateService;
import com.rootzwy.bbs.admin.util.JWTUtil;
import com.rootzwy.bbs.common.exception.BusinessException;
import com.rootzwy.bbs.common.messagecode.BusinessErrorCode;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Date;

/**
 * @author zwy
 * @date 2022/1/31
 */
@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Resource
    private PrivateService privateService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 添加了注解则不用校验
        Annotation annotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAdminToken.class);
        if (ObjectUtil.isNotNull(annotation)) {
            return true;
        }
        // 获取 Token
        String tokenName = AdminConstant.ADMIN_TOKEN;
        // Header
        String token = request.getHeader(tokenName);
        // Path
        if (StrUtil.isEmpty(token)) {
            token = request.getParameter(tokenName);
        }
        // Cookie
        if (StrUtil.isEmpty(token) && ArrayUtil.isNotEmpty(request.getCookies())) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(AdminConstant.ADMIN_TOKEN)) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        // 是否存在
        if (StrUtil.isEmpty(token)) {
            response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
            throw new BusinessException(BusinessErrorCode.TOKEN_INVALID);
        }

        JWTDO jwtInfo = privateService.getJWTInfo();
        // 是否过期
        DecodedJWT decodedJWT = JWTUtil.handler(jwtInfo.getKey()).verifyAndGetDecodedToken(token);
        if (decodedJWT.getExpiresAt().compareTo(new Date()) < 0) {
            response.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
            throw new BusinessException(BusinessErrorCode.TOKEN_TIMEOUT);
        }
        // 设置线程用户变量
        AdminContext adminContext = new AdminContext();
        adminContext.setId(decodedJWT.getClaim("id").asLong());
        adminContext.setAccount(decodedJWT.getClaim("account").asString());
        adminContext.setRole(decodedJWT.getClaim("role").asInt());
        AdminHolder.set(adminContext);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                @Nullable Exception ex) {
        AdminHolder.remove();
    }

}
