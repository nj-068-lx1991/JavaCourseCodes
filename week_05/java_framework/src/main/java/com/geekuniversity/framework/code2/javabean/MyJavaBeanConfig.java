package com.geekuniversity.framework.code2.javabean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Created by lx_068
 * @date 2021.12.05
 */
@Configuration
public class MyJavaBeanConfig {

    @Bean
    public MyJavaBeanConfig javaCodeExample() {
        return new MyJavaBeanConfig();
    }
}
