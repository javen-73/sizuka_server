package com.javen.sizuka.modules.accountItem.service;

import com.javen.sizuka.model.AccountItem;
import com.javen.sizuka.modules.accountItem.mapper.AccountItemMapper;
import com.javen.sizuka.utils.ReturnDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by javen on 2017/12/1.
 */
@Service
public class AccountItemService {

    @Autowired
    private AccountItemMapper accountItemMapper;

    public ReturnDTO getHeadAndBodyData(Integer userId, Integer bookId) {
        if(userId==null||bookId==null){
            return ReturnDTO.buildFaildReturnDTO("查询失败");
        }

        return null;
    }
}
