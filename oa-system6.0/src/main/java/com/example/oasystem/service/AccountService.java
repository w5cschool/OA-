package com.example.oasystem.service;
import com.example.oasystem.controller.RespStat;
import com.example.oasystem.entity.*;
import com.example.oasystem.mapper.AccountMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author tianyaliaowang
 * @date 2020/11/12 - 15:12
 */
@Service
public class AccountService {
    @Resource
    AccountMapper accountMapper;


    /**
     * 验证账户密码
     * 根据是否能找到账号密码来判断账号密码是否正确
     * @param loginName
     * @param password
     * @return
     */
    public Account findLoginNameAndPassword(String loginName, String password) {

        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andLoginNameEqualTo(loginName).andPasswordEqualTo(password);

        List list = accountMapper.selectByExample(accountExample);

        return list.size() == 0 ? null:(Account)list.get(0);

    }


    /**
     * 注册账户
     * @param account
     * @return
     */
    public RespStat insert(Account account) {
        int key = accountMapper.insertSelective(account);
        if (key==1){
            return RespStat.build(200,"注册成功！");
        }else {
            return RespStat.build(500,"注册失败");
        }
    }

    /**
     * 注册时根据用户名查看用户名是否已经存在
     * @param loginName
     * @return
     */
    public String findByLoginName(String loginName) {
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andLoginNameEqualTo(loginName);

        List list = accountMapper.selectByExample(accountExample);

        return list.size()==0 ? null:"exist";

    }

    /**
     * 查询用户列表
     * @param startNum
     * @param size
     * @return
     */
    public PageInfo<Account> findAccountList(int startNum,int size) {
        PageHelper.startPage(startNum,size);
        AccountExample accountExample = new AccountExample();

        List<Account> list = accountMapper.selectByExample(accountExample);

        return new PageInfo<>(list,2);

    }
}
