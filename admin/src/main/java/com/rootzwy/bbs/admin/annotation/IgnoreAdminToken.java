package com.rootzwy.bbs.admin.annotation;

import java.lang.annotation.*;

/**
 * @author zwy
 * @date 2022/1/31
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreAdminToken {

    String value() default "";

}
