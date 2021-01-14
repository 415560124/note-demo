package com.rhy.note.proxy.cglib.two;

import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * @author Rhy
 * @title cglib方法测试类
 * @description cglib方法测试类
 * @createTime 2021年01月04日 16:05:00
 * @modifier：Rhy
 * @modification_time：2021-01-04 16:05
 */
public class CglibMethodInterceptTest {
//    public static void main(String[] args) {
//        //创建一个Enhancer对象
//        Enhancer enchaner = new Enhancer();
//        //设置被代理的类
//        enchaner.setSuperclass(Student.class);
//        //创建一个回调接口
//        Callback interceptor = new MethodInterceptor() {
//
//            @Override
//            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
//                    throws Throwable {
//                System.out.println("原方法名是 ： " + method.getName());
//                System.out.println("原方法声明的类为 " + method.getDeclaringClass());
//                String res = (String)proxy.invokeSuper(obj, args);
//                System.out.println("我是 " +  res);
//                System.out.println("我调用结束了");
//                return res;
//            }
//        };
//        enchaner.setCallback(interceptor);
//        Student student = (Student) enchaner.create();
//        System.out.println(student.getStuName());
//
//    }
    public static void main(String[] args) {
        //创建一个Enhancer对象
        Enhancer enchaner = new Enhancer();
        //设置被代理的类
        enchaner.setSuperclass(Student.class);

        //创建一个回调接口
        Callback interceptor = new MethodInterceptor() {

            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
                    throws Throwable {
                System.err.println("原方法名是 ： " + method.getName());
                System.err.println("原方法声明的类为 " + method.getDeclaringClass());
                System.err.println("我是 " + (String) proxy.invokeSuper(obj, args));
                System.err.println("我调用结束了");
                return proxy.invokeSuper(obj, args);
            }
        };
        CallbackFilter callbackFilter = new CallbackFilter() {

            @Override
            public int accept(Method method) {
                int flag = 0;
                if ("getStuName".equals(method.getName())) {
                    System.err.println("我将此方法过滤掉了，不对该方法进行拦截");
                    return 1;
                }
                return 0;
            }
        };
        Callback[] callbacks = new Callback[] { interceptor, NoOp.INSTANCE };
        enchaner.setCallbackFilter(callbackFilter);
        enchaner.setCallbacks(callbacks);
        Student student = (Student) enchaner.create();
        System.err.println(student.getStuName());
        System.err.println(student.getRename());
    }
}
