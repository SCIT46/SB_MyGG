package com.mygg.sb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UrlController {
    @RequestMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        // React의 index.html로 모든 비정적 요청을 전달
        return "forward:/index.html";
    }
}
