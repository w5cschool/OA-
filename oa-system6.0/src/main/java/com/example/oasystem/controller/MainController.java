package com.example.oasystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能概览
 *
 * 1、登录
 *      1.1登录验证
 *      1.2退出登录
 * 2、注册
 *      检测账号是否存在
 *      上传图片到本地磁盘D:/workplace/myFile
 * 3、首页     显示图片
 * 4、用户列表
 *      accountList
 *      显示每个用户的头像
 * 5、角色列表
 *      显示角色列表
 *          role
 *          id
 *          删除
 *          修改
 * 6、权限列表
 *      显示权限列表
 *      crud
 *
 -------------未完成-----
 * 7、添加用户
 *
 * 8、添加角色
 *
 * 9、添加权限
 *
 * 10、修改权限
 *
 * 11、修改角色
 *
 * 12、权限控制
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















