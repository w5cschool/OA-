package com.example.oasystem.controller;

import com.example.oasystem.entity.User;
import com.example.oasystem.service.UserService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.context.properties.bind.DefaultValue;
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
     * 实现上传头像的注册
     * @param loginName     用户名
     * @param password      密码
     * @param filename      头像图片
     *                      注意这里的前端提交的表单应该是"multipart/form-data"类型的
     * @param map           返回给客户端的map
     * @return
     */
    @PostMapping("register")
    public String registerP(@RequestParam("loginName") String loginName
            , @RequestParam("password") String password
            , @RequestParam("filename") MultipartFile filename
            ,ModelMap map){

        //注册用户，保存到数据库
        RespStat stat = userService.register(loginName,password);

        //将stat信息传给前端
        map.addAttribute("stat",stat);

        //上传用户的图片
        System.out.println("图片名称:" + filename.getOriginalFilename());

        try {
            //获取项目的classpath的地址
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            System.out.println(path);

            //上传到的地址，最终地址：classpath+static/pictures/
            File upload = new File(path.getAbsolutePath(), "static/pictures/");
            System.out.println("upload:" + upload);

            //将图片上传到upload下，并以filename的名字为文件名保存下来
            filename.transferTo(new File(upload+"/"+filename.getOriginalFilename()));

        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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


    @GetMapping("profile")
    public String profile(){

        return "account/profile";

    }
}


