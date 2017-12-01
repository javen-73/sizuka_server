package com.javen.sizuka.modules.accountbook.mapper;

import com.javen.sizuka.model.AccountBook;
import com.javen.sizuka.model.AccountBookExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface AccountBookMapper {
    long countByExample(AccountBookExample example);

    int deleteByExample(AccountBookExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountBook record);

    int insertSelective(AccountBook record);

    List<AccountBook> selectByExample(AccountBookExample example);

    AccountBook selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountBook record, @Param("example") AccountBookExample example);

    int updateByExample(@Param("record") AccountBook record, @Param("example") AccountBookExample example);

    int updateByPrimaryKeySelective(AccountBook record);

    int updateByPrimaryKey(AccountBook record);

    List<AccountBook> findAccountBooksByUserId(@Param("userId") Integer userId);
}