package com.example.oasystem.mapper;

import com.example.oasystem.entity.Role;
import com.example.oasystem.entity.RoleExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * RoleMapper继承基类
 */
@Mapper
@Repository
public interface RoleMapper extends MyBatisBaseDao<Role, Integer, RoleExample> {
}