package com.rhy.note.proxy.cglib.one;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Rhy
 * @title Cglib代理测试类
 * @description Cglib代理测试类
 * @createTime 2021年01月04日 15:44:00
 * @modifier：Rhy
 * @modification_time：2021-01-04 15:44
 */
public class CglibAgentTest {
//    public static void main(String[] args) {
//        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(CglibService.class);
//        // 设置enhancer的回调对象
//        enhancer.setCallback(new MyMethodInterceptor());
//        // 创建代理对象
//        CglibService proxy= (CglibService)enhancer.create();
//        // 通过代理对象调用目标方法
//        proxy.sayHello();
//        proxy.sayOthers("小明");
//    }
    public static void main(String[] args) {
        //创建一个Enhancer对象
        Enhancer enchaner = new Enhancer();
        //设置被代理的类
        enchaner.setSuperclass(CglibService.class);
        //创建一个回调接口
        Callback interceptor = new MethodInterceptor() {

            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
                    throws Throwable {
                System.err.println("原方法名是 ： " + method.getName());
                System.err.println("原方法声明的类为 " + method.getDeclaringClass());
                System.err.println("我是 " + (String) proxy.invokeSuper(obj, args));
                System.err.println("我调用结束了");
                return null;
            }
        };
        enchaner.setCallback(new MyMethodInterceptor());
        CglibService student = (CglibService) enchaner.create();
        student.sayHello();

    }
}
