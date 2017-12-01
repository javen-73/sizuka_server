package com.javen.sizuka.modules.accountbook.service;

import com.javen.sizuka.model.AccountBook;
import com.javen.sizuka.modules.accountbook.mapper.AccountBookMapper;
import com.javen.sizuka.utils.ReturnDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by javen on 2017/12/1.
 */
@Service
public class AccountBookService {
    private Logger logger = LoggerFactory.getLogger(AccountBookService.class);
    @Autowired
    private AccountBookMapper accountBookMapper;


    public ReturnDTO getAccountBooks(Integer userId) {
        List<AccountBook> books = accountBookMapper.findAccountBooksByUserId(userId);
        if(books==null&&books.size()<1){
            logger.error("没有查询到账簿");
            return ReturnDTO.buildFaildReturnDTO("没有查询到账簿");
        }
        return ReturnDTO.buildSuccessReturnDTO("查询成功",books);
    }

    public ReturnDTO getDefaultAccountBooks(Integer userId) {
        List<AccountBook> books = accountBookMapper.findAccountBooksByUserId(userId);
        if(books.size()>0){
            return ReturnDTO.buildSuccessReturnDTO("默认账簿查询成功",books.get(0));
        }
        return ReturnDTO.buildFaildReturnDTO("查询默认账簿失败");
    }

    public ReturnDTO addAccountBook(Integer userId, String bookName) {
        if(userId==null||bookName==null||"".equals(bookName)){
            return ReturnDTO.buildFaildReturnDTO("添加失败");
        }
        AccountBook accountBook = new AccountBook();
        accountBook.setUserId(userId);
        accountBook.setBookName(bookName);
        accountBook.setCreateTime(new Date());
        accountBookMapper.insertSelective(accountBook);
        return ReturnDTO.buildSuccessReturnDTO("创建成功",accountBook);
    }
}
