package com.zhiyou100.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//标明 需要被装配的对象
@Retention(RetentionPolicy.RUNTIME)
public @interface Require {
}
