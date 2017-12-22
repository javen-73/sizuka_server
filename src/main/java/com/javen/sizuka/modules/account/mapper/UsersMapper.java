package com.javen.sizuka.modules.account.mapper;

import com.javen.sizuka.model.Users;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UsersMapper {

    Users findUserByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    int insertUsers(@Param("user") Users user);

    int findUserNameIsExist(String username);

    int savePosition(@Param("position") String position);

    List<Map<String,Object>> findPosition();
}