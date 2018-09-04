package com.zhiyou100.listener;

/*
*@ClassName:ContextLoadListener
 @Description:TODO
 @Author:
 @Date:2018/8/22 16:09 
 @Version:v1.0
*/


import com.zhiyou100.factory.BeanFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoadListener implements ServletContextListener {
    //在tomcat启动的时候，会执行contextInitialized方法
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //System.out.println("----------------hello world---------------");
        try {
            BeanFactory beanFactory = new BeanFactory();
            //缓存工厂  需要在servlet获取工厂
            //因为servletContext是全局的  任何一个servlet都可以获取此对象
            sce.getServletContext().setAttribute("beanFactory",beanFactory);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
