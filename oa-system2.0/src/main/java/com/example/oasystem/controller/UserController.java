package com.example.oasystem.controller;

import com.example.oasystem.entity.User;
import com.example.oasystem.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author tianyaliaowang
 * @date 2020/11/3 - 13:59
 */
@Controller
@RequestMapping("/account")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("login")
    public String login(){
        return "account/login";
    }

    /**
     * 异步校验账户密码是否正确
     * @param loginName
     * @param password
     * @return      success 成功
     */
    @RequestMapping("validDataAccount")
    @ResponseBody
    public String validDataAccount(String loginName,String password,HttpServletRequest request){

        //验证密码是否正确
        User account = userService.findByLoginNameAndPassword(loginName,password);
        if (account == null){
            return "登录失败";
        }else{
            //登录成功，将user写入session里，
            // 这样在不同的controller里或前端页面上都可以访问到他了
            request.getSession().setAttribute("account",account);
            return "success";
        }
    }

    /**
     * 退出登录----清除session
     * @param request
     * @return
     */
    @RequestMapping("/logOut")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("account");
        return "account/logOut";
    }


    /**
     *
     * @param map
     * @param pageNum       第几页
     * @param pageSize      每页显示多少个数据
     * @return
     */
    @RequestMapping("/list")
    public String accountList(
            ModelMap map,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "2") int pageSize
            ){
        //使用PageInfo来分页
        PageInfo<User> page = userService.findAll(pageNum,pageSize);
        map.addAttribute("page",page);
        return "account/list";

    }



    @RequestMapping("/deleteById")
    @ResponseBody
    public RespStat deleteById(int id) {
        // 标记一下 是否删除成功？  status
        RespStat stat = userService.deleteById(id);

        return stat;

    }

    @RequestMapping("/deleteById2")
    @ResponseBody
    public RespStat deleteById2(int id){
        RespStat stat = userService.deleteById2(id);
        return stat;
    }


}


