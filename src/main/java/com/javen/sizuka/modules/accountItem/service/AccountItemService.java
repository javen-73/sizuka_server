package com.javen.sizuka.modules.accountItem.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javen.sizuka.model.ItemType;
import com.javen.sizuka.model.AccountItem;
import com.javen.sizuka.model.MoneyInOut;
import com.javen.sizuka.model.Page;
import com.javen.sizuka.modules.accountItem.mapper.AccountItemMapper;
import com.javen.sizuka.modules.accountItem.mapper.ItemTypeMapper;
import com.javen.sizuka.utils.MoneyUtil;
import com.javen.sizuka.utils.ParameterUtil;
import com.javen.sizuka.utils.ReturnDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * Created by javen on 2017/12/1.
 */
@Service
public class AccountItemService {
    private Logger logger = LoggerFactory.getLogger(AccountItemService.class);
    @Autowired
    private AccountItemMapper accountItemMapper;
    @Autowired
    private ItemTypeMapper itemTypeMapper;

    public ReturnDTO getHeadAndBodyData(Map<String, String> params) {
        String userId = params.get("userId");
        String bookId = params.get("bookId");
        String date = params.get("date");
        Integer page = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
        if (userId == null || bookId == null) {
            return ReturnDTO.buildFaildReturnDTO("查询失败");
        }
        LocalDate localDate = null;
        if (date != null) {
            localDate = LocalDate.parse(date);
        } else {
            localDate = LocalDate.now();
        }
        //这个月日期的范围 1- lastday
        LocalDate firstDayOfThisMonth = localDate.with(TemporalAdjusters.firstDayOfMonth()); // 2017-12-01
        LocalDate lastDayOfThisMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());
        logger.info("first day is :{}", firstDayOfThisMonth);
        logger.info("last day is :{}", lastDayOfThisMonth);
        int earn = accountItemMapper.selectDashBoard(userId, bookId, firstDayOfThisMonth, lastDayOfThisMonth,1);
        int disburse = accountItemMapper.selectDashBoard(userId, bookId, firstDayOfThisMonth, lastDayOfThisMonth,0);
        MoneyInOut moneyInOut = new MoneyInOut();
        moneyInOut.setMoney_in(earn);
        moneyInOut.setMoney_out(disburse);
        moneyInOut.setBalance(moneyInOut.getMoney_in() + moneyInOut.getMoney_out());

        Map<String, Object> result = new HashMap<String, Object>();
        Page p = new Page();
        PageHelper.startPage(page, p.getPageSize());
        List<AccountItem> accountItems = accountItemMapper.selectAccountItems(userId, bookId, firstDayOfThisMonth, lastDayOfThisMonth);
        PageInfo<AccountItem> info = new PageInfo<AccountItem>(accountItems);
        List<Object> items = info.getList().stream().map(i -> {
            String price = MoneyUtil.save2Digit((Integer) i.getPrice() / 100.00d);
            i.setPrice(price);
            return i;
        }).collect(toList());
        p.setData(items);
        p.setPages(info.getPages());
        p.setPage(page);
        p.setLastPage(info.isIsLastPage());
        result.put("earn", MoneyUtil.save2Digit(moneyInOut.getMoney_in() / 100d)); //收入
        result.put("disburse", MoneyUtil.save2Digit(moneyInOut.getMoney_out() / 100d)); //支出
        result.put("balance", MoneyUtil.save2Digit(moneyInOut.getBalance() / 100d)); //结账
        result.put("page", p);
        return ReturnDTO.buildSuccessReturnDTO("查询成功", result);
    }

    public ReturnDTO saveBill(Map<String, Object> params) {
        ParameterUtil.checkParamMap(params,new ArrayList<String>(){{add("remark");}});
        Object userId = params.get("userId");
        Object bookId =params.get("bookId");
        Object money =params.get("money");
        Object iconType = params.get("iconType");
        Object priceType = params.get("priceType"); //收入支出
        Object date = params.get("date");
        Object remark = params.get("remark");

        AccountItem accountItem = new AccountItem();
        accountItem.setUserId(Integer.parseInt(userId.toString()));
        accountItem.setBookId(Integer.parseInt(bookId.toString()));
        accountItem.setPrice(Integer.parseInt(money.toString()));
        accountItem.setPriceType(Integer.parseInt(iconType.toString())); //icon Type
        accountItem.setItemStatus(Integer.parseInt(priceType.toString())); //收入支出，别搞混
        accountItem.setCreateTime(LocalDateTime.of(LocalDate.parse(date.toString()), LocalTime.now()));
        accountItem.setRemark((String) remark);
        accountItemMapper.insertSelective(accountItem);
        return ReturnDTO.buildSuccessReturnDTO(accountItem);
    }

    public ReturnDTO getItemTypes() {
        List<ItemType> itemTypes = itemTypeMapper.selectAllTypes();
        Map map = HandlerItemTypes(itemTypes);
        return ReturnDTO.buildSuccessReturnDTO("查询成功", map);
    }

    private Map HandlerItemTypes(List<ItemType> itemTypes) {
        List<ItemType> out = itemTypes.stream().filter(itemType -> {
            return itemType.getStatus() == 0;
        }).collect(toList());
        List<ItemType> in = itemTypes.stream().filter(itemType -> {
            return itemType.getStatus() == 1;
        }).collect(toList());
        List<List> out_list = new ArrayList<List>();
        List<List> in_list = new ArrayList<List>();
        List temp = new ArrayList();
        int counter = 1;
        for (int i = 0; i < out.size(); i++) {
            if (i % 6 == 0 && i != 0) {
                out_list.add(temp);
                counter++;
                //换一个新的list
                temp = new ArrayList();
            }
            temp.add(out.get(i));
        }
        //剩下的list可能未满5个，直接装入map
        out_list.add(temp);
        //将引用重新指向一个新的list
        temp = new ArrayList();
        //重置counter
        counter = 1;
        for (int i = 0; i < in.size(); i++) {
            if (i % 6 == 0 && i != 0) {
                in_list.add(temp);
                counter++;
                temp = new ArrayList();
            }
            temp.add(in.get(i));
        }
        in_list.add(temp);
        Map result = new HashMap();
        result.put(0, out_list);
        result.put(1, in_list);
        return result;
    }

}
