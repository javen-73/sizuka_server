package com.javen.sizuka.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by javen on 2017/11/29.
 */
public class ParameterUtil {
    private static final Logger log = LoggerFactory.getLogger(ParameterUtil.class);
    /**
     * 从request中获得参数Map，并返回可读的Map
     *
     * @param request
     * @return
     */
    public static Map<String,Object> getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }
    /**
     * 从request中获得参数Map，并返回可读的Map
     *
     * @param request
     * @return
     */
    public static Map<String,String> getParameterMapString(HttpServletRequest request) {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        Map<String,String> returnMap = new HashMap<String,String>();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }
    public static String ObjectConvertJson(Object rule) {
        ObjectMapper jsonStu = new ObjectMapper();
        String str = "";
        try {
            str = jsonStu.writeValueAsString(rule);
        } catch (Exception e) {
        }
        return str;
    }
    /**
     * 检查map参数是否为空
     * @param parameterMap
     * @return
     */
    public static ReturnDTO checkParamMap(Map<String, Object> parameterMap,List<String> param) {
        Set<String> keySet = parameterMap.keySet();
        for (String key:keySet){
            //过滤 可选参数
            if(param.contains(key))
                continue;
            Object temp=parameterMap.get(key);
            if(temp==null){
                log.error("查询失败参数不能为空:{}",key);
                return ReturnDTO.buildFaildReturnDTO("参数不能为空:"+key);
            }
        }
        return null;
    }


}
