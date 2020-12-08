package com.example.oasystem.mapper;

import com.example.oasystem.entity.Account;
import com.example.oasystem.entity.AccountExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * AccountMapper继承基类
 */
@Mapper
@Repository
public interface AccountMapper extends MyBatisBaseDao<Account, Integer, AccountExample> {
}