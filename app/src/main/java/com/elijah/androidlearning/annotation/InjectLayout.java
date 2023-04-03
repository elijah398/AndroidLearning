package com.elijah.androidlearning.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @FileName null.java
 * @Description TODO
 * @Author 80254912
 * @Date 2023/3/28
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectLayout {
    // Activity 布局文件
    int value() default -1;
}
