package com.geekuniversity.framework.code2.auto;

import org.springframework.stereotype.Component;

/**
 * 自动注解方式，Bean装配
 * @author created by lx_068
 */
@Component
public class AutoWireExample {
    public AutoWireExample() {
        System.out.println("Construct Example");
    }

    public void info() {
        System.out.println("Auto wiring example");
    }
}
