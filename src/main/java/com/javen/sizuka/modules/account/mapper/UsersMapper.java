package com.javen.sizuka.modules.account.mapper;

import com.javen.sizuka.model.Users;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UsersMapper {

    Users findUserByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    int insertUsers(@Param("user") Users user);

    int findUserNameIsExist(String username);
}