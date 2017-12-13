package com.javen.sizuka.modules.accountItem.mapper;

import com.javen.sizuka.model.AccountItem;

import java.time.LocalDate;
import java.util.List;

import com.javen.sizuka.model.MoneyInOut;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountItemMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(AccountItem record);

    int insertSelective(AccountItem record);


    AccountItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(AccountItem record);

    MoneyInOut selectDashBoardInfoByThisMoth(String userId, String bookId, LocalDate firstDayOfThisMonth, LocalDate lastDayOfThisMonth);

    List<AccountItem> selectAccountItems(String userId, String bookId, LocalDate firstDayOfThisMonth, LocalDate lastDayOfThisMonth);
}