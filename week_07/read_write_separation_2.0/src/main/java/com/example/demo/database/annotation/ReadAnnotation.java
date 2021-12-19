package com.example.demo.database.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 读写分离 - 动态切换数据源版本 1.0
 *
 * @author Created by lx_068
 * @date 2021/12/19
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ReadAnnotation {
}