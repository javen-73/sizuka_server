package com.javen.sizuka.modules.accountItem.controller;

import com.javen.sizuka.modules.accountItem.service.AccountItemService;
import com.javen.sizuka.utils.CommonConstants;
import com.javen.sizuka.utils.ReturnDTO;
import com.javen.sizuka.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by javen on 2017/12/1.
 */
@Controller
@RequestMapping("/accountItem")
public class AccountItemController {
    private Logger logger = LoggerFactory.getLogger(AccountItemController.class);
    @Autowired
    private AccountItemService accountItemService;
    @Autowired
    private TokenUtil tokenUtil;

    //下拉刷新
    @RequestMapping("/downPull")
    @ResponseBody
    public ReturnDTO downPull(HttpServletRequest request, @RequestParam("userId") Integer userId,@RequestParam("bookId") Integer bookId){
        String token = request.getHeader(CommonConstants.AUTHORIZATION);
        Boolean auth = tokenUtil.isAuth(token);
        if(!auth){
            logger.error("登录授权信息无效");
            return ReturnDTO.buildFaildReturnDTO("登录授权信息无效");
        }
        ReturnDTO dto = null;
        try{
            accountItemService.getHeadAndBodyData(userId,bookId,null);
        }catch (Exception e){
            logger.error("查询账簿详情失败:{}",e);
            dto=ReturnDTO.buildFaildReturnDTO("查询账簿详情失败");
        }
        return dto;
    }
}
