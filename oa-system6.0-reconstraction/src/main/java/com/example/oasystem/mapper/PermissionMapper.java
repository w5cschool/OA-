package com.example.oasystem.mapper;

import com.example.oasystem.entity.Permission;
import com.example.oasystem.entity.PermissionExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * PermissionMapper继承基类
 */
@Mapper
@Repository
public interface PermissionMapper extends MyBatisBaseDao<Permission, Integer, PermissionExample> {
}