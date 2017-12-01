package com.javen.sizuka.modules.accountbook.controller;

import com.javen.sizuka.modules.accountbook.service.AccountBookService;
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
@RequestMapping("/accountBook")
public class AccountBookController {
    private Logger logger = LoggerFactory.getLogger(AccountBookController.class);
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private AccountBookService accountBookService;

    @RequestMapping("/getAccountBooks")
    @ResponseBody
    public ReturnDTO getAccountBooks(HttpServletRequest request,@RequestParam("userId") Integer userId){
        String token = request.getHeader(CommonConstants.AUTHORIZATION);
        Boolean auth = tokenUtil.isAuth(token);
        if(!auth){
            logger.error("登录授权信息无效");
            return ReturnDTO.buildFaildReturnDTO("登录授权信息无效");
        }
        ReturnDTO dto= null;
        try{
            dto=accountBookService.getAccountBooks(userId);
        }catch (Exception e){
            logger.error("查询账簿失败:{}",e);
            dto=ReturnDTO.buildFaildReturnDTO("查询账簿失败");
        }
        return dto;
    }

    @RequestMapping("/getDefaultAccountBook")
    @ResponseBody
    public ReturnDTO getDefaultAccountBook(HttpServletRequest request,@RequestParam("userId") Integer userId){
        String token = request.getHeader(CommonConstants.AUTHORIZATION);
        Boolean auth = tokenUtil.isAuth(token);
        if(!auth){
            logger.error("登录授权信息无效");
            return ReturnDTO.buildFaildReturnDTO("登录授权信息无效");
        }
        ReturnDTO dto= null;
        try{
            dto=accountBookService.getDefaultAccountBooks(userId);
        }catch (Exception e){
            logger.error("查询账簿失败:{}",e);
            dto=ReturnDTO.buildFaildReturnDTO("查询账簿失败");
        }
        return dto;
    }
    @RequestMapping("/addAccountBook")
    @ResponseBody
    public ReturnDTO addAccountBook(HttpServletRequest request,@RequestParam("userId")Integer userId,@RequestParam("bookName") String bookName){
        String token = request.getHeader(CommonConstants.AUTHORIZATION);
        Boolean auth = tokenUtil.isAuth(token);
        if(!auth){
            logger.error("登录授权信息无效");
            return ReturnDTO.buildFaildReturnDTO("登录授权信息无效");
        }
        ReturnDTO dto=null;
        try {
            dto = accountBookService.addAccountBook(userId, bookName);
        }catch (Exception e){
            logger.error("添加账簿失败:{}",e);
            dto=ReturnDTO.buildFaildReturnDTO("添加账簿失败");
        }
        return dto;
    }
}
