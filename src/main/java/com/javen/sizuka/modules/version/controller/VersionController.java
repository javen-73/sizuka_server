package com.javen.sizuka.modules.version.controller;

import com.javen.sizuka.modules.version.service.VersionService;
import com.javen.sizuka.utils.ReturnDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by javen on 2017/12/21.
 */
@Controller
@RequestMapping("/version")
public class VersionController {
    private  static  final Logger logger = LoggerFactory.getLogger(VersionController.class);
    @Autowired
    private VersionService versionService;
    @RequestMapping("/lastVersion")
    @ResponseBody
    public Object lastVersion(){
        ReturnDTO dto = null;
        try {
           dto = versionService.lastVersion();
        }catch (Exception e){
            logger.error("查询版本出错:{}",e);
            dto = ReturnDTO.buildFaildReturnDTO("查询版本出错");
        }
        return dto;
    }
}
