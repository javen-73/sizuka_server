package com.javen.sizuka.modules.account.controller;

import com.javen.sizuka.modules.account.mapper.UsersMapper;
import com.javen.sizuka.model.Users;
import com.javen.sizuka.modules.account.service.UsersService;
import com.javen.sizuka.utils.ReturnDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * Created by javen on 2017/11/28.
 */
@RestController
@RequestMapping("/account")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UsersService usersService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ReturnDTO login(@RequestBody Users user){
        if(user.getUsername()==null||user.getPassword()==null){
            logger.error("登录失败:{用户名或密码为空}");
            return ReturnDTO.buildFaildReturnDTO("登录失败,用户名或密码不能为空");
        }
        ReturnDTO dto= null;
        try {
            dto = usersService.loginAccount(user.getUsername(),user.getPassword());
        }catch (Exception e){
            logger.error("登录失败:{}",e);
            dto=ReturnDTO.buildFaildReturnDTO("登录失败");
        }
        return dto;
    }

    @RequestMapping(value="/create",method = RequestMethod.POST)
    public ReturnDTO create(@RequestBody Users user){
        if(user.getUsername()==null||user.getPassword()==null){
            logger.error("注册失败:{用户名或密码为空}");
            return ReturnDTO.buildFaildReturnDTO("注册失败,用户名或密码不能为空");
        }
        if(user.getAliasName()==null){
            logger.error("注册失败:{昵称不能为空}");
            return ReturnDTO.buildFaildReturnDTO("注册失败,昵称不能为空");
        }
        ReturnDTO dto=null;
        try {
            dto = usersService.createAccount(user);
        }catch (Exception e){
            logger.error("注册失败:{}",e);
            dto=ReturnDTO.buildFaildReturnDTO("注册失败");
        }
        return dto;

    }


}
