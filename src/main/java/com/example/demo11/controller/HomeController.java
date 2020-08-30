package com.example.demo11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @Author:  Eyes of dreams
 * @Date: 2020/8/26 17:15
 * @Description: 配置默认欢迎界面
 **/
@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String homePage() {
        return "forward:index.html";
    }
}