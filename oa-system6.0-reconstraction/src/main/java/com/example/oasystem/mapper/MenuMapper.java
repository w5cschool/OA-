package com.example.oasystem.mapper;

import com.example.oasystem.entity.Menu;
import com.example.oasystem.entity.MenuExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * MenuMapper继承基类
 */
@Mapper
@Repository
public interface MenuMapper extends MyBatisBaseDao<Menu, Integer, MenuExample> {
}