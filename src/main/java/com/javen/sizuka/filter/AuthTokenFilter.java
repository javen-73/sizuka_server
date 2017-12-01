package com.javen.sizuka.filter;

import com.alibaba.fastjson.JSON;
import com.javen.sizuka.utils.CommonConstants;
import com.javen.sizuka.utils.ReturnDTO;
import com.javen.sizuka.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by javen on 2017/12/1.
 * 停用,跨域相关有点问题停用
 */
//@Component
//@WebFilter(filterName = "tokenFilter", urlPatterns = "/accountBook")
public class AuthTokenFilter implements Filter{
    private Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
    @Autowired
    private TokenUtil tokenUtil;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    @Deprecated
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        try{
            request= (HttpServletRequest) req;
            response= (HttpServletResponse) resp;
        }catch (Exception e){
            logger.error("转换HttpServletRequest失败:{}",e);
        }
        String token = request.getHeader(CommonConstants.AUTHORIZATION);
        Boolean auth = tokenUtil.isAuth(token);
        if(auth){
            chain.doFilter(request,response);
        }else {
            ReturnDTO dto = ReturnDTO.buildFaildReturnDTO("登录授权信息无效");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            //过滤器返回的时候跨域问题
            response.setHeader("Access-Control-Allow-Origin","*");
            response.setHeader("Access-Control-Allow-Headers", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            PrintWriter out = null;
            try {
                out = response.getWriter();
                out.append(JSON.toJSONString(dto));
            }catch (Exception e){
                logger.error("filter wirte the json data is error");
            }finally {
                if (out != null) {
                    out.close();
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
