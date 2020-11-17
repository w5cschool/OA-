package com.example.oasystem.controller.restful;

import com.example.oasystem.controller.RespStat;
import com.example.oasystem.entity.Permission;
import com.example.oasystem.service.PermissionService;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author tianyaliaowang
 * @date 2020/11/12 - 20:28
 */
@Controller
@RequestMapping("rest")
public class RestfulController {

    @Resource
    PermissionService ps;

    /**
     * 添加权限
     *
     * @return
     */
    @GetMapping("add")
    public String add(){
        return "api/v1/permission/add";
    }

    @PostMapping("add")
    @ResponseBody
    public RespStat addP(Permission permission){

        System.out.println("permission"+ToStringBuilder.reflectionToString(permission));

        RespStat stat = ps.save(permission);

        return stat;
    }


    /**
     * 修改权限：
     *      1、用户点击修改权限get请求，传入要修改的权限的id值到controller
     *      2、controller将传入的id值查找到对应的permission，并通过map传给前端
     *      3、前端根据传来的permission判断是否有crud的权限，并将按钮勾选上
     *      4、用户提交post请求后，将修改后的值序列化存为一个对象传给后端的controller
     *      5、后端把接根据收到的对象permission的id修改它的权限
     *
     * @param id
     * @param map
     * @return
     */

    //get请求

    @GetMapping("modifyPermission")
    public String modifyPermissionG(@RequestParam("id") int id, ModelMap map){

        //根据传入的id值查找对应的permission
        Permission permission = ps.findPermissionById(id);
        //将查找到的permission传给前端
        map.addAttribute("permission",permission);
        return "api/v1/permission/modify";
    }

    //post请求

    @PostMapping("modifyPermission")
    @ResponseBody
    public RespStat modifyPermissionP(Permission permission){

        //修改permission
        return ps.update(permission);
    }

}

