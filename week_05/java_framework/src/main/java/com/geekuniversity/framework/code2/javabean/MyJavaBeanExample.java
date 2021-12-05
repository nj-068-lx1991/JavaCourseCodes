package com.geekuniversity.framework.code2.javabean;

import org.springframework.stereotype.Component;

/**
 * Java代码方式，Bean装配
 * @author lw
 */
@Component
public class MyJavaBeanExample {
    public MyJavaBeanExample() {
        System.out.println("Construct Example");
    }

    public void info() {
        System.out.println("Auto wiring example");
    }
}
