package com.example.oasystem.service;

import com.example.oasystem.controller.RespStat;
import com.example.oasystem.entity.User;
import com.example.oasystem.entity.UserExample;
import com.example.oasystem.mapper.UserMapper;
import com.github.pagehelper.PageInfo;
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

    /**
     * 使用PageHelper的PageInfo来分页
     * @param start         开始页
     * @param size          显示多少个
     * @return
     */
    public PageInfo<User> findAll(int start,int size) {

        PageHelper.startPage(start,size);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIsNotNull();

        List<User> accountList = userMapper.selectByExample(userExample);

        //前面都是正常操作，只是将返回的结果List的变为返回一个PageInfo
        return new PageInfo<>(accountList,5);
    }


    public RespStat deleteById(int id) {

        // 1. 要提示用户
        // 2. 通过删除标记 数据永远删不掉    / update 只做增，而不是直接改表内容  // 历史数据 表（数据库）  -> 写文本log
        int row = userMapper.deleteByPrimaryKey(id);

        if(row == 1) {

            return RespStat.build(200);
        }else {
            return RespStat.build(500,"删除出错");
        }
    }


    public RespStat register(String loginName,String password) {

        User user = new User();
        user.setPassword(password);
        user.setLoginName(loginName);
        int key = userMapper.insert(user);
        System.out.println(key);
        if (key==1){
            return new RespStat(200,"注册成功","ss");
        }else{
            return new RespStat(500,"注册失败","ss");
        }

    }

    public String findRoleById(int id) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdEqualTo(id);

        List<User> list = userMapper.selectByExample(userExample);
        if (list.size()==1){
            return list.get(0).getRole();
        }else{
            return "查找失败";
        }

    }

}
