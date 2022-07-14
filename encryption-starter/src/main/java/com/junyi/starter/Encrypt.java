package com.junyi.starter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 加密注解，添加到方法上
 * @time: 2022/7/14 11:24
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Encrypt {
}
