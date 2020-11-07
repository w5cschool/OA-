package com.example.oasystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tianyaliaowang
 * @date 2020/11/3 - 13:33
 */
@Controller
public class MainController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    /**
     * 登录成功后的页面
     * @return
     */
    @RequestMapping("/index")
    public String index2(){
        return "index";
    }
}















