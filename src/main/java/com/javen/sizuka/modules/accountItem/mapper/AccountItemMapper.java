package com.javen.sizuka.modules.accountItem.mapper;

import com.javen.sizuka.model.AccountItem;
import com.javen.sizuka.model.AccountItemExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountItemMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(AccountItem record);

    int insertSelective(AccountItem record);


    AccountItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(AccountItem record);
}