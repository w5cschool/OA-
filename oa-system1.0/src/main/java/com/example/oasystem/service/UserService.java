package com.example.oasystem.service;

import com.example.oasystem.entity.User;
import com.example.oasystem.entity.UserExample;
import com.example.oasystem.mapper.UserMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.github.pagehelper.PageHelper;

/**
 * @author tianyaliaowang
 * @date 2020/11/3 - 13:34
 */
@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    public User findByLoginNameAndPassword(String loginName, String password) {

        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andLoginNameEqualTo(loginName)
                .andPasswordEqualTo(password);

        List<User> list = userMapper.selectByExample(userExample);
        //如果list大小为0，则表示没有找到匹配的用户，
        // 否则返回第一个用户（正常情况下，数据库里的用户名是唯一的，只会有一个）

        return list.size() == 0? null:list.get(0);

    }

    public List<User> findAll(int start,int size) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIsNotNull();
        PageHelper.startPage(start,size);
        List<User> accountList = userMapper.selectByExample(userExample);
        return accountList;
    }


}
