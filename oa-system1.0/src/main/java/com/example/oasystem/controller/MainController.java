package com.example.oasystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author tianyaliaowang
 * @date 2020/11/3 - 13:33
 */
@Controller
public class MainController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    /**
     * 登录成功后的页面
     * @return
     */
    @GetMapping("/index")
    public String index2(){
        return "index";
    }
}















