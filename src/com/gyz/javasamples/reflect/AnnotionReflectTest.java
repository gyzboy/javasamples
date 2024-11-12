package com.gyz.javasamples.reflect;

import com.gyz.javasamples.annotation.FruitColor;
import com.gyz.javasamples.annotation.FruitName;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

/**
 * Created by guoyizhe on 16/9/7.
 * 邮箱:gyzboy@126.com
 */
public class AnnotionReflectTest {
    public static void main(String[] args) {
        try {
            // 根据“类名”获取 对应的Class对象
            Class<?> cls = Class.forName("com.gyz.javasamples.reflect.PPerson");

            Field[] fields = cls.getDeclaredFields();

            for(Field field :fields){
                if(field.isAnnotationPresent(MyAnnotation.class)){
                    MyAnnotation fruitName = field.getAnnotation(MyAnnotation.class);
                    System.out.println("id = " + fruitName.id());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * MyAnnotation是自定义个一个Annotation
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    int id() default -1;
}

/**
 * MyAnnotation 是Person的注解。
 */
class PPerson {
    @MyAnnotation(id = 123)
    int id;
}