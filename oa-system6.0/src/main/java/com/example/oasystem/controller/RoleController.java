package com.example.oasystem.controller;

import com.example.oasystem.entity.Role;
import com.example.oasystem.service.RoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @author tianyaliaowang
 * @date 2020/11/12 - 20:27
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    /**
     * 角色列表
     * @param startNum
     * @param size
     * @param map
     * @return
     */
    @RequestMapping("roleList")
    public String roleList(@RequestParam(value = "pageNum",defaultValue = "1") int startNum,
                           @RequestParam(value = "size",defaultValue = "2") int size,
                           ModelMap map) {

        PageInfo<Role> page = roleService.findRoleList(startNum, size);

        map.addAttribute("page", page);

        return "role/roleList";
    }

}
