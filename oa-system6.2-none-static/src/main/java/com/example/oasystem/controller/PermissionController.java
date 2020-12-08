package com.example.oasystem.controller;

import com.example.oasystem.entity.Permission;
import com.example.oasystem.entity.Role;
import com.example.oasystem.service.PermissionService;
import com.example.oasystem.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tianyaliaowang
 * @date 2020/11/12 - 20:28
 */
@Controller
@RequestMapping("api/v1/permission")
public class PermissionController {
    @Resource
    PermissionService permissionService;

    @Resource
    RoleService roleService;
    /**
     * 权限列表
     * @param startNum
     * @param size
     * @param map
     * @return
     */
    @RequestMapping("permissionList")
    public String permissionList(@RequestParam(value = "pageNum",defaultValue = "1") int startNum,
                                 @RequestParam(value = "size",defaultValue = "5") int size,
                                 ModelMap map){

        PageInfo<Permission> page = permissionService.findPermissionList(startNum,size);

        map.addAttribute("page",page);

        return "api/v1/permission/permissionList";

    }


    /**
     * 显示某个角色下拥有的权限列表
     *
     * 1、点击某个角色的查看权限，将该角色的roleId传给controller
     * 2、controller将接收到的roleId，找出该role对象，将该对象传给前端，以显示这是那个角色的权限
     * 3、controller继续将该角色的roleId通过findPermissionListByRoleId传给service
     * 4、service在将这个roleId通过findPermissionListByRoleId传给PermissionMapper
     * 5、由于PermissionMapper没有findPermissionListByRoleId这个方法，所以需要我们构造这个方法
     * 6、然后再在PermissionMapper对应的PermissionMapper.xml文件中写对于的sql语句查找该roleId对应的所有的权限.
     * 7、将查找到的权限列表用一个resultMap接受
     *
     * @param roleId        角色的id
     * @return
     */
    @GetMapping("permissionListByRole")
    public String permissionListByRole(@RequestParam("roleId") int roleId,
                                       @RequestParam(value = "pageNum",defaultValue = "1") int startNum,
                                       @RequestParam(value = "size",defaultValue = "2") int size,
                                       ModelMap map){

        //根据传入的iroleId查找出数据库中role，传给前端，用于显示这个角色的role名称
        Role role = roleService.findRoleByRoleId(roleId);

        //
        PageInfo<Permission> plist = permissionService.findPermissionListByRoleId(roleId,startNum,size);
        map.addAttribute("page",plist);
        map.addAttribute("role",role);
        return "api/v1/permission/permissionListByRole";
    }


}
