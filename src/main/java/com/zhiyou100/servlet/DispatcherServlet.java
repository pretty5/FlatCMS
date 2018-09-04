package com.zhiyou100.servlet;


import com.zhiyou100.annotation.MethodType;
import com.zhiyou100.annotation.RequestMapping;
import com.zhiyou100.factory.BeanFactory;
import com.zhiyou100.handler.RequestHandler;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/*
*@ClassName:DispatcherServlet
 @Description:TODO
 @Author:
 @Date:2018/8/20 16:52 
 @Version:v1.0
*/
public class DispatcherServlet extends HttpServlet {

    private Map<String, RequestHandler> uriToHandler = new HashMap();

    //servlet的初始化方法  用来加载src下面的所有controller的类及方法。
    @Override
    public void init() throws ServletException {
        //获取classes目录
        URL resource = DispatcherServlet.class.getClassLoader().getResource("");
        Collection<File> files = FileUtils.listFiles(new File(resource.getPath()), new String[]{"class"}, true);
        for (File file :
                files) {
            //System.out.println(file.getAbsolutePath());
            //根据文件的绝对路径，获取class的全限定类名
            String className = getClassName(file.getAbsolutePath());
            try {
                Class<?> clazz = Class.forName(className);
                //判断该类是否是controller类型。

                if (clazz.isAnnotationPresent(RequestMapping.class)) {
                    //获取controller中所有的方法
                    //获取请求路径
                    RequestMapping mapping = clazz.getAnnotation(RequestMapping.class);
                    String baseUrl = mapping.value();
                    Method[] methods = clazz.getDeclaredMethods();
                    for (Method method :
                            methods) {
                        if (method.isAnnotationPresent(RequestMapping.class)) {
                            //封装 class method
                            RequestHandler handler = new RequestHandler(className, method.getName());
                           //判断此方法是否只能使用post
                            if (method.isAnnotationPresent(MethodType.class)){
                                MethodType methodType = method.getAnnotation(MethodType.class);
                                if (methodType.value().equals("post")){
                                    //只能接收post
                                    handler.setOnlyPost(true);
                                };
                            }
                            //获取方法级别的请求路径
                            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                            String methodUrl = requestMapping.value();
                            uriToHandler.put(baseUrl + methodUrl, handler);
                        }
                    }
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        //System.out.println(resource);

    }

    private String getClassName(String absolutePath) {
        //先截取  获取包名 加类名  然后  替换
        String s = absolutePath.substring(absolutePath.indexOf("classes") + 8, absolutePath.lastIndexOf("."));
        //将“/”换成“。”
        s = s.replace("\\", ".");
        System.out.println(s);
        return s;

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从servletcontext获取工厂
        BeanFactory beanFactory = (BeanFactory) getServletContext().getAttribute("beanFactory");
        //得到请求路径
        String requestURI = req.getRequestURI();
        RequestHandler requestHandler = uriToHandler.get(requestURI);
        if (requestHandler != null) {
            if (req.getMethod().equals("GET")){
                if (requestHandler.isOnlyPost()){
                    return;
                }
            }
           requestHandler.handle(beanFactory,req, resp);
        }

    }

}
