package com.example.oasystem.controller;

import com.example.oasystem.entity.Permission;
import com.example.oasystem.service.PermissionService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @author tianyaliaowang
 * @date 2020/11/12 - 20:28
 */
@Controller
@RequestMapping("api/v1/permission")
public class PermissionController {
    @Resource
    PermissionService permissionService;

    /**
     * 权限列表
     * @param startNum
     * @param size
     * @param map
     * @return
     */
    @RequestMapping("permissionList")
    public String permissionList(@RequestParam(value = "pageNum",defaultValue = "1") int startNum,
                                 @RequestParam(value = "size",defaultValue = "2") int size,
                                 ModelMap map){

        PageInfo<Permission> page = permissionService.findPermissionList(startNum,size);

        map.addAttribute("page",page);

        return "api/v1/permission/permissionList";

    }
}
