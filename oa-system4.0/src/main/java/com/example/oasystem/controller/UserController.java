package com.example.oasystem.controller;

import com.example.oasystem.entity.User;
import com.example.oasystem.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("register")
    public String registerG(){
        return "account/register";
    }

    /**
     * 注册
     * @param loginName
     * @param password
     * @param map
     * @return
     */
    @PostMapping("register")
    public String registerP(@RequestParam("loginName") String loginName,@RequestParam("password") String password, ModelMap map){

        RespStat stat = userService.register(loginName,password);

        map.addAttribute("stat",stat);

        return "account/register";

    }





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
     *用PageHelper的PageInfo分页显示用户
     * @param map
     * @param pageNum       第几页
     * @param pageSize      每页显示多少个数据
     * @return
     */
    @RequestMapping("/list")
    public String accountList(
            ModelMap map,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize
            ){
        //使用PageInfo来分页
        PageInfo<User> page = userService.findAll(pageNum,pageSize);
        map.addAttribute("page",page);
        return "account/list";

    }

    /**
     * 通过id删除用户
     * @param needDeleteId            要被删除的id账户
     * @param sessionId     当前页面登录的Id
     * @return
     */
    @RequestMapping("/deleteById")
    @ResponseBody
    public RespStat deleteById(int needDeleteId,int sessionId) {

        System.out.println(sessionId);

        //通过id查找当前登录的用户的role
        String role = userService.findRoleById(sessionId);

        if ("admin".equals(role)){
            // 标记一下 是否删除成功？  status
            RespStat stat = userService.deleteById(needDeleteId);
            return stat;
        }else {
            return new RespStat(500,"无权限","无权限");
        }





    }


}


