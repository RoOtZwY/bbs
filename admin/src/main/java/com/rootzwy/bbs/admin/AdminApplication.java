package com.rootzwy.bbs.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zwy
 * @date 2022/2/18
 */
@SpringBootApplication(scanBasePackages = {"com.rootzwy.bbs.admin", "com.rootzwy.bbs.common"})
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class);
    }

}
