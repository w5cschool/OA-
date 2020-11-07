package com.example.oasystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tianyaliaowang
 * @date 2020/11/4 - 22:10
 */
@Controller
public class MainController {

    @RequestMapping("index")
    public String index(){

        return "index";
    }
}
