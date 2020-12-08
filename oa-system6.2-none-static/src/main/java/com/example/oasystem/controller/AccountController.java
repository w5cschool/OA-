package com.example.oasystem.controller;

import com.example.oasystem.entity.Account;
import com.example.oasystem.entity.Permission;
import com.example.oasystem.service.AccountService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/account")
public class AccountController {

    @Resource
    AccountService accountService;

    /**
     *     1、登录
     */
    @GetMapping("login")
    public String loginG(){
        return "account/login";
    }


    /**
     * 登录验证
     * @param loginName
     * @param password
     * @return
     */
    @RequestMapping("/validDataAccount")
    @ResponseBody
    public String validDataAccount(@RequestParam("loginName") String loginName,
                                   @RequestParam(("password"))String password,
                                   HttpServletRequest request){

        Account account = accountService.findLoginNameAndPassword(loginName,password);
        if (account!=null){
            request.getSession().setAttribute("account",account);
            return "success";
        }else {
            return "fail";
        }

    }


    /**
     * 退出登录
     *      删除存在session中的存入的account属性即可
     * @param request
     * @return
     */
    @RequestMapping("logOut")
    public String logOut(HttpServletRequest request){
        request.getSession().removeAttribute("account");
        return "account/logOut";
    }



    @GetMapping("register")
    public String registerG(){

        return "account/register";
    }

    /**
     * 注册账号
     * 验证账号是否存在
     * loginName
     * password
     * image
     *      获取当前项目路径
     *      存储路径
     *      上传
     * @return
     */

    @PostMapping("register")
    public RespStat register(@RequestParam("loginName") String loginName,
                           @RequestParam("password") String password,
                           @RequestParam("image")MultipartFile image,
                           ModelMap map
                           ){
        //检验账号是否存在
        String exist = accountService.findByLoginName(loginName);

        //如果不存在，则注册
        if (null==exist){
            try {
                //上传的路径
                File upload = new File("/D:/workplace/myFile");
                System.out.println(upload);
                //上传图片
                image.transferTo(new File(upload+image.getOriginalFilename()));

                Account account = new Account();
                account.setLoginName(loginName);
                account.setPassword(password);
                account.setImage(image.getOriginalFilename());
                //注册用户（插入）
                RespStat stat = accountService.insert(account);
                map.addAttribute("stat",stat);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            //如果账号存在则返回“账号已存在”
            RespStat stat = RespStat.build(400,"账号已存在");
            map.addAttribute("stat",stat);
            return stat;
        }
        return null;
    }


    /**
     * 显示权限列表
     * @param startNum  开始页数
     * @param size      显示多少页
     * @param map       传给前端的map
     * @return
     */
    @RequestMapping("accountList")
    public String permissionList(@RequestParam(value = "pageNum",defaultValue = "1") int startNum,
                                 @RequestParam(value = "size",defaultValue = "5") int size,
                                 ModelMap map){

        PageInfo<Account> page = accountService.findAccountList(startNum,size);

        map.addAttribute("page",page);

        return "account/accountList";

    }
}
