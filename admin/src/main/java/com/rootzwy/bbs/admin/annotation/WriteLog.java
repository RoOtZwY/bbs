package com.rootzwy.bbs.admin.annotation;

import java.lang.annotation.*;

/**
 * @author zwy
 * @date 2022/3/16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface WriteLog {

}
