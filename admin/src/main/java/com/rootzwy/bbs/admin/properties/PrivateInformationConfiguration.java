package com.rootzwy.bbs.admin.properties;

import com.rootzwy.bbs.admin.service.impl.PrivateServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zwy
 * @date 2022/2/19
 */
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(value = {PrivateInformationProperties.class})
public class PrivateInformationConfiguration {

    private PrivateInformationProperties privateInformationProperties;

    @Bean
    public PrivateServiceImpl privateService() {
        PrivateServiceImpl privateService = new PrivateServiceImpl();
        privateService.setProperties(privateInformationProperties);
        return privateService;
    }

}
