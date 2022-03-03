package com.rhy.note.demo;

import com.rhy.note.NoteDemoApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: Herion Lemon
 * @date: 2022/2/15 20:39
 * @slogan: 如果你想攀登高峰，切莫把彩虹当梯子
 * @description:
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException {
//        //反射
//        Class aClass = Class.forName("com.rhy.note.demo.User");
//        //3.创建对象
//        User user = (User)aClass.newInstance();
//        //1.能够通过反射获取对象的属性和方法
//        for (int i = 0; i < aClass.getDeclaredFields().length; i++) {
//            System.out.println(aClass.getDeclaredFields()[i].getName());
//            Field declaredField = aClass.getDeclaredFields()[i];
//            declaredField.setAccessible(true);
//            declaredField.set(user,"s");
//        }
//        System.out.println(user);
//        System.out.println("==================================================");
//        //2.对属性进行赋值，反射执行方法
//        for (int i = 0; i < aClass.getDeclaredMethods().length; i++) {
//            System.out.println(aClass.getDeclaredMethods()[i].getName());
//        }
//        System.out.println("==================================================");
//        System.out.println(aClass.getDeclaredMethods()[2].invoke(user));
//        System.out.println("==================================================");
//        //4.获得类基本信息，以及注解信息
//        for (int i = 0; i < aClass.getAnnotations().length; i++) {
//            System.out.println(aClass.getAnnotations()[i].annotationType().getName());
//        }
//        System.out.println(aClass.getName());
//        System.out.println(aClass.getSimpleName());

        //IO
        File file = new File("C:\\Users\\41556\\Desktop\\老婆头像.png");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[fileInputStream.available()];
        fileInputStream.read(bytes);

        File file2 = new File("C:\\Users\\41556\\Desktop\\老婆头像1.png");
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        fileOutputStream.write(bytes);

        fileInputStream.close();
        fileOutputStream.close();
    }
}
