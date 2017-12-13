package com.javen.sizuka.modules.accountItem.controller;

import com.javen.sizuka.modules.accountItem.service.AccountItemService;
import com.javen.sizuka.utils.CommonConstants;
import com.javen.sizuka.utils.ParameterUtil;
import com.javen.sizuka.utils.ReturnDTO;
import com.javen.sizuka.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
    public ReturnDTO downPull(HttpServletRequest request){
        String token = request.getHeader(CommonConstants.AUTHORIZATION);
        Boolean auth = tokenUtil.isAuth(token);
        if(!auth){
            logger.error("登录授权信息无效");
            return ReturnDTO.buildFaildReturnDTO("登录授权信息无效");
        }
        Map<String, String> params = ParameterUtil.getParameterMapString(request);
        ReturnDTO dto = null;
        try{
            dto=accountItemService.getHeadAndBodyData(params);
        }catch (Exception e){
            logger.error("查询账簿详情失败:{}",e);
            dto=ReturnDTO.buildFaildReturnDTO("查询账簿详情失败");
        }
        return dto;
    }

    //下拉刷新
    @RequestMapping("/upflush")
    @ResponseBody
    public ReturnDTO upflush(HttpServletRequest request){
        String token = request.getHeader(CommonConstants.AUTHORIZATION);
        Boolean auth = tokenUtil.isAuth(token);
        if(!auth){
            logger.error("登录授权信息无效");
            return ReturnDTO.buildFaildReturnDTO("登录授权信息无效");
        }
        Map<String, String> params = ParameterUtil.getParameterMapString(request);
        ReturnDTO dto = null;
        try{
            dto=accountItemService.getHeadAndBodyData(params);
        }catch (Exception e){
            logger.error("查询账簿详情失败:{}",e);
            dto=ReturnDTO.buildFaildReturnDTO("查询账簿详情失败");
        }
        return dto;
    }

    //保存
    @RequestMapping("/saveBill")
    @ResponseBody
    public ReturnDTO saveBill(HttpServletRequest request, @RequestBody Map<String,Object> params){
        String token = request.getHeader(CommonConstants.AUTHORIZATION);
        Boolean auth = tokenUtil.isAuth(token);
        if(!auth){
            logger.error("登录授权信息无效");
            return ReturnDTO.buildFaildReturnDTO("登录授权信息无效");
        }
        ReturnDTO dto = null;
        try{
            dto=accountItemService.saveBill(params);
        }catch (Exception e){
            logger.error("记一笔失败:{}",e);
            dto=ReturnDTO.buildFaildReturnDTO("记一笔失败");
        }
        return dto;
    }

    @RequestMapping("/getItemType")
    @ResponseBody
    public ReturnDTO getItemType(){
        ReturnDTO dto = null;
        try{
            dto=accountItemService.getItemTypes();
        }catch (Exception e){
            logger.error("查询账簿详情失败:{}",e);
            dto=ReturnDTO.buildFaildReturnDTO("查询账簿详情失败");
        }
        return dto;
    }


}
