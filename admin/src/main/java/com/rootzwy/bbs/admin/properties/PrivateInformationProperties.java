package com.rootzwy.bbs.admin.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zwy
 * @date 2022/2/19
 */
@Getter
@Setter
@ConfigurationProperties(prefix = PrivateInformationProperties.PREFIX)
public class PrivateInformationProperties {

    public static final String PREFIX = "private-key";

    private String jwtKey;

    private String md5Key;

}
