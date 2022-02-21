package com.rootzwy.bbs.admin.config;

import com.rootzwy.bbs.admin.annotation.LoginAdmin;
import com.rootzwy.bbs.admin.context.AdminContext;
import com.rootzwy.bbs.admin.context.AdminHolder;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author zwy
 * @date 2022/1/31
 */
@Component
public class AdminArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginAdmin.class)
                && parameter.getParameterType().equals(AdminContext.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        return AdminHolder.getOrEmpty();
    }
}
