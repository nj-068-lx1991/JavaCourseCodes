package com.rpc.core.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * RPC provider service 初始化注解
 *
 * group,version,targs 都有默认值，是为了兼容以前的版本
 *
 * @author Created by lx_068
 * @date 2022/01/09
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ProviderService {

    /**
     * 对应 API 接口名称
     * @return API service
     */
    String service();

    /**
     * 分组
     * @return group
     */
    String group() default "default";

    /**
     * version
     * @return version
     */
    String version() default "default";

    /**
     * tags:用于简单路由
     * 多个标签使用逗号分隔
     * @return tags
     */
    String tags() default "";

    /**
     * 权重：用于加权负载均衡
     * @return int
     */
    int weight() default 1;
}
