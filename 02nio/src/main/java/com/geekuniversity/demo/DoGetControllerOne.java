package com.geekuniversity.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class DoGetControllerOne {
    @RequestMapping("/doGetControllerOne")
    public String doGet() {
        return "hello,nio1";
    }
}
