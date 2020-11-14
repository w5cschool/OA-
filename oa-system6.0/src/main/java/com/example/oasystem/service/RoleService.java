package com.example.oasystem.service;

import com.example.oasystem.entity.Role;
import com.example.oasystem.entity.RoleExample;
import com.example.oasystem.mapper.RoleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tianyaliaowang
 * @date 2020/11/12 - 20:34
 */
@Service
public class RoleService {

    @Resource
    RoleMapper roleMapper;
    /**
     * 查询角色列表
     * @param startNum
     * @param size
     * @return
     */
    public PageInfo<Role> findRoleList(int startNum, int size) {

        PageHelper.startPage(startNum,size);
        RoleExample roleExample = new RoleExample();

        List<Role> list = roleMapper.selectByExample(roleExample);

        return new PageInfo<>(list,2);

    }
}
