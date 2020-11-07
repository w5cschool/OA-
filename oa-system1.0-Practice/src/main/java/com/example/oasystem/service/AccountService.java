package com.example.oasystem.service;

import com.example.oasystem.entity.User;
import com.example.oasystem.entity.UserExample;
import com.example.oasystem.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tianyaliaowang
 * @date 2020/11/4 - 22:41
 */
@Service
public class AccountService {

    @Resource
    UserMapper userMapper;

    public List<User> findLoginNameAndPassword(User account) {

        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andPasswordEqualTo(account.getPassword())
                .andLoginNameEqualTo(account.getLoginName());

        List<User> list = userMapper.selectByExample(userExample);

        return list;

    }

    public List<User> findAll() {
        UserExample userExample = new UserExample();

        userExample.createCriteria().andIdIsNotNull();

        List<User> accountList = userMapper.selectByExample(userExample);
        return accountList;
    }
}
