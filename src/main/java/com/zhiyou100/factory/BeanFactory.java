package com.zhiyou100.factory;


import com.zhiyou100.annotation.Bean;
import com.zhiyou100.annotation.Require;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

/*
*@ClassName:BeanFactory
 @Description:TODO
 @Author:
 @Date:2018/8/22 10:58 
 @Version:v1.0
*/
public class BeanFactory {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        BeanFactory beanFactory = new BeanFactory();
    }

    //用来保存创建的对象的
    private Map<Class,Object> beans=new HashMap<>();


    public Object getBean(Class type){
        return beans.get(type);
    }

    public BeanFactory() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //初始化工厂
        //生产零件---创建对象
        //找到所有类上有@bean注解的类
        List<Class> classes=getClassesWithBean();
        //创建对象  放入beans
        for (Class clazz : classes) {
            beans.put(clazz,clazz.newInstance());
        }
        //装配----完善对象内的属性
        Set<Class> keySet = beans.keySet();
        for (Class clazz: keySet){
            Object instance = beans.get(clazz);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                //判断该字段是否需要被装配
                if (field.isAnnotationPresent(Require.class)){
                    //根据字段类型，去工厂里边找到 对应类型的对象   之后设置进该字段
                    //因为字段是私有属性，不能直接设置，需要先让其可以访问
                    field.setAccessible(true);
                    field.set(instance,beans.get(field.getType()));
                }
            }
        }

    }

    private List<Class> getClassesWithBean() throws ClassNotFoundException {
        ArrayList<Class> classes = new ArrayList<>();
        URL resource = BeanFactory.class.getClassLoader().getResource("");

        Collection<File> files = FileUtils.listFiles(new File(resource.getPath()), new String[]{"class"}, true);
        for (File file : files) {
            //System.out.println(file.getAbsolutePath());
            //根据文件的绝对路径，获取class的全限定类名
            String className = getClassName(file.getAbsolutePath());

            Class<?> clazz = Class.forName(className);
            if (clazz.isAnnotationPresent(Bean.class)){
                classes.add(clazz);
            }

        }
        return classes;

    }
    private String getClassName(String absolutePath) {
        //先截取  获取包名 加类名  然后  替换
        String s = absolutePath.substring(absolutePath.indexOf("classes") + 8, absolutePath.lastIndexOf("."));
        //将“/”换成“。”
        s = s.replace("\\", ".");
        //System.out.println(s);
        return s;

    }
}
