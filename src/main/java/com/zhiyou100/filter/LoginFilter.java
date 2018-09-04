package com.zhiyou100.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
*@ClassName:LoginFilter
 @Description:TODO
 @Author:
 @Date:2018/8/27 10:47 
 @Version:v1.0
*/
/*实现登陆的过滤  如果已经登陆  则可以进入后台管理页面，如果没有登陆  则进入登陆页面*/
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    /*具体的过滤逻辑的实现*/
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            //判断用户当前是否是登陆行为  /index/index.do
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String requestURI = req.getRequestURI();
        if (requestURI.equals("/login/login.do")){
            //进入后续的过滤器 如果没有进入servlet请求
            //System.out.println("before filer");
            chain.doFilter(request,response);
            //System.out.println("after filter");
            //return;
        }else{
            HttpSession session = req.getSession();
            Object user = session.getAttribute("user");
            //如果session中user为null,说明之前没有登陆，无权请求后续页面 ，转发至登陆
            if (user==null){
                resp.sendRedirect("/login.html");
                //return;
            }else{
                ///说明用户之前已经登陆  有权请求后续页面，
                chain.doFilter(request,response);
                //return;
            }

        }

    }

    @Override
    public void destroy() {

    }
}
