package com.example.oasystem.service;

import com.example.oasystem.controller.RespStat;
import com.example.oasystem.entity.Permission;
import com.example.oasystem.entity.PermissionExample;
import com.example.oasystem.mapper.PermissionMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tianyaliaowang
 * @date 2020/11/12 - 20:34
 */
@Service
public class PermissionService {

    @Resource
    PermissionMapper permissionMapper;

    /**
     * 查询权限列表
     * @param startNum
     * @param size
     * @return
     */
    public PageInfo<Permission> findPermissionList(int startNum, int size) {
        PageHelper.startPage(startNum,size);
        PermissionExample permissionExample = new PermissionExample();
        List<Permission> list  = permissionMapper.selectByExample(permissionExample);
        return new PageInfo<>(list,4);

    }

    public RespStat save(Permission permission) {

        int key = permissionMapper.insertSelective(permission);
        return key==1?RespStat.build(200,"成功"):RespStat.build(500,"失败");

    }

    public Permission findPermissionById(int id) {

        return permissionMapper.selectByPrimaryKey(id);

    }

    public RespStat update(Permission permission){

        int key = permissionMapper.updateByPrimaryKeySelective(permission);
        return key==1?RespStat.build(200,"成功"):RespStat.build(500,"失败");
    }

}
