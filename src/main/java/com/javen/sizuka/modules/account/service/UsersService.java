package com.javen.sizuka.modules.account.service;

import com.javen.sizuka.model.AccountBook;
import com.javen.sizuka.model.Users;
import com.javen.sizuka.modules.account.mapper.UsersMapper;
import com.javen.sizuka.modules.accountbook.mapper.AccountBookMapper;
import com.javen.sizuka.utils.EncryptUtils;
import com.javen.sizuka.utils.ReturnDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created by javen on 2017/11/30.
 */
@Service
public class UsersService {
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private AccountBookMapper accountBookMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Transactional
    public ReturnDTO createAccount(Users user) {
        int userNameIsExist = usersMapper.findUserNameIsExist(user.getUsername());
        if(userNameIsExist>0){
            return ReturnDTO.buildFaildReturnDTO("用户名已存在");
        }
        Date now = new Date();
        //设置用户
        //设置默认头像
        user.setProfilePicture("../assets/imgs/default.jpg");
        user.setCreateTime(now);
        //创建完账户后，返回了ID
        usersMapper.insertUsers(user);
        //创建默认账簿
        AccountBook accountBook = new AccountBook();
        accountBook.setUserId(user.getId());
        accountBook.setBookName("默认账簿");
        accountBook.setStatus(1);
        accountBook.setCreateTime(now);
        accountBookMapper.insertSelective(accountBook);
        return ReturnDTO.buildSuccessReturnDTO("注册成功",user);
    }

    public ReturnDTO loginAccount(String username, String password) throws NoSuchAlgorithmException {
        Users users = usersMapper.findUserByUsernameAndPassword(username,password);
        if(users==null){
            return ReturnDTO.buildFaildReturnDTO("请检查用户名和密码");
        }
        String token = EncryptUtils.encryptToMD5(users.getId() + users.getUsername());
        stringRedisTemplate.opsForValue().set(token,users.getId().toString());
        users.setToken(token);
        return ReturnDTO.buildSuccessReturnDTO("登录成功",users);
    }
}
