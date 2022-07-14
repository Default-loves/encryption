package com.junyi.starter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 解密注解，可添加到方法或者参数上
 * @time: 2022/7/14 11:24
 * @version: 1.0
 * @author: junyi Xu
 * @description:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.PARAMETER})
public @interface Decrypt {
}
