package com.zhiyou100.util;

import com.google.gson.Gson;
import com.zhiyou100.model.House;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
*@ClassName:BeanUtil
 @Description:TODO
 @Author:
 @Date:2018/8/28 15:47 
 @Version:v1.0
*/
/*
更正：BeanUtil.parseFromRequest 方法有误
原因如下:house中的有些字段不是字符串，而请求参数为字符串，直接用反射 会报参数类型异常
解决办法：添加依赖：commons-beanUtils  commons封装好的反射工具类
<dependency>
      <groupId>commons-beanutils</groupId>
      <artifactId>commons-beanutils</artifactId>
      <version>1.9.3</version>
    </dependency>

    方法一 ：
    Field[] fields = clazz.getDeclaredFields();
        T instance = clazz.newInstance();
        for (Field field:fields) {
            field.setAccessible(true);
            //判断请求参数是否为空 如果为空，则不用设置，因为instance中构造完毕后，默认所有字段都是null
            if (request.getParameter(field.getName())==null){
                continue;
            }else{
            //如果请求参数不为空，则利用ConvertUtils.convert(String value,class type)方法，此方法的作用是将字符串 转换成指定类型
                Object convertedValue = ConvertUtils.convert(request.getParameter(field.getName()), field.getType());
                field.set(instance,convertedValue);
            }
        }
        return instance;

    方法二：思路：将请求参数转换成map  然后将map转json，之后再将json转对象
   代码如下：
   //获取请求参数的map，因为该map 默认是<String,String[]>类型的，因此需要将
   //该map转成<String,String>类型的
     Map parameterMap = request.getParameterMap();

        HashMap<String, String> map = new HashMap<>();

        Set<String> keySet = parameterMap.keySet();

        for (String key :
                keySet) {
            String[] values = (String[]) parameterMap.get(key);

            map.put(key,values[0]);
        }
       //转换成<String,String>类型后
        Gson gson = new Gson();
        //先将map转json：gson.toJson(map)，之后调用gson.fromJson(gson.toJson(map), clazz)
        //将json转换成对象
        T instance = gson.fromJson(gson.toJson(map), clazz);
        return instance;
 */
public class BeanUtil {
    public static<T> T parseFromRequest(HttpServletRequest request,Class<T> clazz) throws IllegalAccessException, InstantiationException, ParseException {
        Field[] fields = clazz.getDeclaredFields();
        T instance = clazz.newInstance();
        for (Field field:fields) {
            field.setAccessible(true);

            if (request.getParameter(field.getName())==null){
                continue;
            }
            if (field.getType().getName().equals("java.util.Date")){
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = dateFormat.parse(request.getParameter(field.getName()));
                field.set(instance,date);
            } else{
                Object convertedValue = ConvertUtils.convert(request.getParameter(field.getName()), field.getType());
                field.set(instance,convertedValue);
            }

           //field.set(instance,request.getParameter(field.getName()));
        }
        return instance;
    }
    public static<T> T parseFromRequestMap(HttpServletRequest request,Class<T> clazz) throws IllegalAccessException, InstantiationException {
        Map parameterMap = request.getParameterMap();
        HashMap<String, String> map = new HashMap<>();
        Set<String> keySet = parameterMap.keySet();
        for (String key :
                keySet) {
            String[] values = (String[]) parameterMap.get(key);
            map.put(key,values[0]);
        }
        Gson gson = new Gson();
        T instance = gson.fromJson(gson.toJson(map), clazz);
        return instance;
    }


    
    //给定一个对象  得到该对象所有字段的值 放入数组
    public static Object[] getFieldsValue(House house) throws IllegalAccessException {
        Field[] fields = house.getClass().getDeclaredFields();
        ArrayList list = new ArrayList();
        for (Field field:fields) {
            field.setAccessible(true);
            Object value = field.get(house);
            list.add(value);
        }
        return list.toArray();

    }
}
