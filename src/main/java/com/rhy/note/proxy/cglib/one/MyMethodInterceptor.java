package com.rhy.note.proxy.cglib.one;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Rhy
 * @title 代码方法类
 * @description 代码方法类
 * @createTime 2021年01月04日 15:40:00
 * @modifier：Rhy
 * @modification_time：2021-01-04 15:40
 */
public class MyMethodInterceptor implements MethodInterceptor {
    /**
     *
     * @param o 代理对象
     * @param method  被代理的对象方法
     * @param objects 方法入参
     * @param methodProxy 代理方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("======插入前置通知======");
        Object object = methodProxy.invokeSuper(o,objects);
        System.out.println("======插入后置通知======");
        return object;
    }
}
