package com.example.oasystem.filter;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * 过滤：
 *      1、是否在ignore列表
 *          在       --通过
 *          不在
 *          -----看该用户是否在session中
 *                      在       通过
 *                      不在      重定向到index
 *
 *
 * @author tianyaliaowang
 * @date 2020/11/5 - 19:27
 */
@Component
public class AccountFilter implements Filter {

    /**
     * 下面的链接都是没有登录就可以访问的，index是不可以访问的，可以用index来测试
     */
    private final String[] IGNORE_URL =
            {"/js/","/css/","images","/account/login","/account/validDataAccount","/account/logOut"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();

        for (String url:IGNORE_URL){
            if (url.startsWith(uri)){
                filterChain.doFilter(request,response);
                return;
            }
        }

        Object account = request.getSession().getAttribute("account");

        if (null==account){
            response.sendRedirect("/index");
        }else{
            filterChain.doFilter(request,response);
        }
    }
}
