package com.rootzwy.bbs.admin.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zwy
 * @date 2022/1/31
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Resource
    private AdminInterceptor adminInterceptor;

    @Resource
    private AdminArgumentResolver adminArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminInterceptor).addPathPatterns("/**").excludePathPatterns("/error");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(adminArgumentResolver);
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // TODO 改为对的url
        corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
//        corsConfiguration.setExposedHeaders();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        FilterRegistrationBean<CorsFilter> filterFilterRegistrationBean =
                new FilterRegistrationBean<>(new CorsFilter(urlBasedCorsConfigurationSource));
        filterFilterRegistrationBean.setName("crossOriginFilter");
        filterFilterRegistrationBean.setOrder(Integer.MIN_VALUE);
        return  filterFilterRegistrationBean;
    }

}

