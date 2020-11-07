package com.example.oasystem.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * filter的实现，实现哪些页面是需要登录才可以访问，哪些页面是不需要登录也可以访问
 *
 * @author tianyaliaowang
 * @date 2020/11/3 - 16:52
 */
@Component
@WebFilter(urlPatterns = "/*")
public class AccountFilter implements Filter {
    /**
     * 列表里的页面是不需要session就可以访问的
     */
    private final String[] IGNORE_URL = {"/account/login","/account/validDataAccount","/account/logOut","/index"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //加载filter 启动之前需要的资源
        System.out.println("-----AccountFilter init----");
    }

    /**
     * 过滤客户是否能访问某个页面
     * 1、是否是ignore里的页面
     *      1.1是   通过
     *      1.2否
     *          1.2.1   查看session中是否有该用户的记录
     *                          是      通过
     *                          否       不通过,并且跳到index
     */
    @Override
    public void doFilter(ServletRequest srq, ServletResponse srp, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) srq;
        HttpServletResponse response = (HttpServletResponse) srp;
        String uri = request.getRequestURI();

        if (inIgnoreUrl(uri)) {
            filterChain.doFilter(request, response);
            return;
        }

        //从session获取account用户
        Object account = request.getSession().getAttribute("account");
        if (null == account) {
            //如果没有该用户的session跳到index页面
            response.sendRedirect("index");
        } else {
            //有这个用户的session则通过，允许访问
            filterChain.doFilter(request, response);
        }
    }

    /**
     * 判断传入的uri是否在IGNORE_UR表里
     * @param uri
     * @return
     */
    public boolean inIgnoreUrl(String uri){
        boolean pass = false;
        //看是否是IGNORE_URL列表里的url，是的话，返回true
        for (String ignoreURL : IGNORE_URL) {
            //判断url头部是否在里面，其下级目录也可以访问
            if (uri.startsWith(ignoreURL)) {
                pass = true;
            }
        }
        return pass;
    }
}

