package com.rootzwy.bbs.admin.annotation;

import java.lang.annotation.*;

/**
 * @author zwy
 * @date 2022/1/31
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginAdmin {

    String value() default "";

}
