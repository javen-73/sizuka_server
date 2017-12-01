package com.javen.sizuka.utils;

import lombok.Data;

/**
 * Created by javen on 2017/11/29.
 */
@Data
public class ReturnDTO {
    private Integer code;
    private String msg;
    private Object data;

    private ReturnDTO(){}
    public static ReturnDTO buildSuccessReturnDTO(Object data){
        ReturnDTO dto = new ReturnDTO();
        dto.setCode(200);
        dto.setMsg("成功");
        dto.setData(data);
        return dto;
    }
    public static ReturnDTO buildSuccessReturnDTO(String msg,Object data){
        ReturnDTO dto = new ReturnDTO();
        dto.setCode(200);
        dto.setMsg(msg);
        dto.setData(data);
        return dto;
    }

    public static ReturnDTO buildFaildReturnDTO(String msg){
        ReturnDTO dto = new ReturnDTO();
        dto.setCode(-1);
        dto.setMsg(msg);
        return dto;
    }
}
