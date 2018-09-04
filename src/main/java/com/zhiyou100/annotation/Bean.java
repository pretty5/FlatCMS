package com.zhiyou100.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/*
*@ClassName:Bean
 @Description:TODO
 @Author:
 @Date:2018/8/22 11:11 
 @Version:v1.0
*/
//表示要被工厂实例化的类
@Retention(RetentionPolicy.RUNTIME)
public @interface Bean {

}
