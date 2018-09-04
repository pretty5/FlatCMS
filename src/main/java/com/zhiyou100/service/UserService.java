package com.zhiyou100.service;

import com.zhiyou100.annotation.Bean;
import com.zhiyou100.annotation.Require;
import com.zhiyou100.dao.UserDao;
import com.zhiyou100.model.User;

import java.sql.SQLException;

/*
*@ClassName:UserSerivce
 @Description:TODO
 @Author:
 @Date:2018/8/27 11:58 
 @Version:v1.0
*/
@Bean
public class UserService {
    @Require
    UserDao userDao;
    public User findUserByNameAndPassword(String name,String password) throws SQLException {
        return userDao.findUserByNameAndPassword(name,password);
    }
 }
