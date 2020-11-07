package com.example.oasystem.mapper;

import com.example.oasystem.entity.User;
import com.example.oasystem.entity.UserExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * UserMapper继承基类
 */
@Mapper
@Repository
public interface UserMapper extends MyBatisBaseDao<User, Integer, UserExample> {
}