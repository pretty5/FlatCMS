package com.zhiyou100.handler;

import com.zhiyou100.factory.BeanFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
*@ClassName:RequestHandler
 @Description:TODO
 @Author:
 @Date:2018/8/20 17:34 
 @Version:v1.0
*/
/*
 对 类和方法的封装
 */
public class RequestHandler {
    //类名
    private String className;//logincontroller
    //方法名
    private String methodName;//index
    //是否只能接收post请求
    private boolean isOnlyPost;


    public RequestHandler(String className, String methodName, boolean isOnlyPost) {
        this.className = className;
        this.methodName = methodName;
        this.isOnlyPost = isOnlyPost;
    }

    public RequestHandler(String className, String methodName) {
        this(className,methodName,false);
    }

    //用来处理请求的方法
    public void handle(BeanFactory factory, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Class<?> clazz = Class.forName(className);

            Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.setAccessible(true);
            //从工厂里边获取（装配好的）对象
            method.invoke(factory.getBean(clazz),request,response);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }  catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public boolean isOnlyPost() {
        return isOnlyPost;
    }

    public void setOnlyPost(boolean onlyPost) {
        isOnlyPost = onlyPost;
    }

}
