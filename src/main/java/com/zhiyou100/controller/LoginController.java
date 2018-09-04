package com.zhiyou100.controller;

import com.google.gson.Gson;
import com.zhiyou100.Resonse.ResponseCode;
import com.zhiyou100.Resonse.ResponseMessage;
import com.zhiyou100.annotation.Bean;
import com.zhiyou100.annotation.MethodType;
import com.zhiyou100.annotation.RequestMapping;
import com.zhiyou100.annotation.Require;
import com.zhiyou100.model.User;
import com.zhiyou100.service.UserService;
import com.zhiyou100.util.ResponseUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/*
*@ClassName:LoginController
 @Description:TODO
 @Author:
 @Date:2018/8/27 11:21 
 @Version:v1.0
*/
@Bean
@RequestMapping("/login")
public class LoginController {
    private static final Logger log=LoggerFactory.getLogger(LoginController.class);
    @Require
    UserService userService;

    @MethodType(value = "post")
    @RequestMapping("/login.do")
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("hello world");
        System.out.println("hello world");
        ResponseMessage responseMessage = new ResponseMessage();
        //获取请求参数
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        //校验
        if (StringUtils.isBlank(name)||StringUtils.isBlank(password)){
            ResponseUtil.responseFailure(resp,"用户名或密码为空",ResponseCode.LOGIN_ERROR_INVALID_PARAMETER);
            return;
        }
        //数据库查询
        try {
            User user = userService.findUserByNameAndPassword(name, password);
            if (user==null){
                //用户名或密码错误
                ResponseUtil.responseFailure(resp,"用户名或密码错误",ResponseCode.LOGIN_ERROR_INVALID_PARAMETER);                return;
            }else{
                //保存用户登陆信息到session
                req.getSession().setAttribute("user",user);
                ResponseUtil.responseSuccess(resp,"login success",ResponseCode.LOGIN_ERROR_INVALID_PARAMETER);
                return;
            }
        } catch (SQLException e) {
            //记录异常
            log.error("login error:",e);
            ResponseUtil.responseFailure(resp,"server error",ResponseCode.LOGIN_ERROR_INVALID_PARAMETER);                return;

        }
    }
    @RequestMapping("/currentUser.do")
    public void getCurrentUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user==null){
            ResponseUtil.response(resp,null,"用户不存在",false,10);
        }else{
            ResponseUtil.response(resp,user.getName(),null,true,20);
        }
    }

    @RequestMapping("/logout.do")
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user==null){
            ResponseUtil.responseFailure(resp,"用户之前没有登陆",10);
        }else{
            req.getSession().removeAttribute("user");
            ResponseUtil.responseSuccess(resp,"退出成功",20);
        }
    }

}
