package com.example.oasystem.controller;

import com.example.oasystem.entity.User;
import com.example.oasystem.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author tianyaliaowang
 * @date 2020/11/4 - 22:24
 */

@Controller
@RequestMapping("/account")
public class AccountController {

    @Resource
    AccountService accountService;

    @RequestMapping("/login")
    public String login(){

        return "account/login";
    }


    /**
     * 登录验证
     *
     * 1、传入登录名和密码
     * 2、将传入的登录名和密码传入Account中
     * 3、将account传给service检验密码是否正确
     * 4、如果正确了返回success
     *          如果错误（没找到对应的登录名和密码）则返回登录名或密码失败
     * 5、
     *
     * @param loginName
     * @param password
     * @return
     */

    @RequestMapping("/validDataAccount")
    @ResponseBody
    public String validDataAccount(String loginName, String password, HttpServletRequest request){

        User account = new User();
        account.setPassword(password);
        account.setLoginName(loginName);

        List<User> list = accountService.findLoginNameAndPassword(account);

        if (list.size()==1){
            request.getSession().setAttribute("account",account);
            return "success";
        }else {
            return "账号或密码错误";
        }
    }


    @RequestMapping("/list")
    public String list(ModelMap map){

        List<User> accountList = accountService.findAll();

        map.addAttribute("accountList",accountList);

        return "account/list";
    }

    @RequestMapping("/logOut")
    public String logOut(HttpServletRequest request){
        request.getSession().removeAttribute("account");
        return "account/logOut";
    }

}
