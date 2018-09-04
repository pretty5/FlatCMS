package com.zhiyou100.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
*@ClassName:EncodingFilter
 @Description:TODO
 @Author:
 @Date:2018/8/27 14:07 
 @Version:v1.0
*/
/*设置字符编码*/
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //请求字符集编码
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        chain.doFilter(request,response);

    }

    @Override
    public void destroy() {

    }
}
