package com.rhy.note.proxy.cglib.one;

/**
 * @author Rhy
 * @title Cglib服务类-被代理类
 * @description Cglib服务类-被代理类
 * @createTime 2021年01月04日 15:40:00
 * @modifier：Rhy
 * @modification_time：2021-01-04 15:40
 */
public class CglibService {
    public CglibService() {
        System.out.println("CglibDao 构造方法");
    }

    /**
     * 该方法不能被子类覆盖,Cglib是无法代理final修饰的方法的
     * @param name
     * @return
     */
    final public String sayOthers(String name){
        System.out.println("CglibDao final sayOthers:"+name);
        return null;
    }

    public void sayHello(){
        System.out.println("CglibDao:sayHello");
    }
}
