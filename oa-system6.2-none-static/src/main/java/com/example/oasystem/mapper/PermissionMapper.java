package com.example.oasystem.mapper;

import com.example.oasystem.entity.Permission;
import com.example.oasystem.entity.PermissionExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PermissionMapper继承基类
 * @author tianyaliaowang
 */
@Mapper
@Repository
public interface PermissionMapper extends MyBatisBaseDao<Permission, Integer, PermissionExample> {
    /**
     * 通过role的id查找该role拥有的所有权限
     * @param roleId
     * @return
     */
    List<Permission> findPermissionListByRoleId(int roleId);

}