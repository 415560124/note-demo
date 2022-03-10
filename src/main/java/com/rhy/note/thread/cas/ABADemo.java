package com.rhy.note.thread.cas;

import org.springframework.objenesis.instantiator.util.UnsafeUtils;
import sun.misc.Unsafe;

public class ABADemo {
    public static void main(String[] args) throws NoSuchFieldException {
        Unsafe unsafe = UnsafeUtils.getUnsafe();
        Entity entity = new Entity();
        long offset = unsafe.objectFieldOffset(entity.getClass().getDeclaredField("x"));
        Thread thread = new Thread(()->{
            int value =  unsafe.getIntVolatile(entity,offset);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            unsafe.compareAndSwapInt(entity,offset,value,5);
            System.out.println(Thread.currentThread().getName()+"："+entity);
        });
        Thread thread1 = new Thread(()->{
            unsafe.compareAndSwapInt(entity,offset,unsafe.getIntVolatile(entity, offset),2);
            System.out.println(Thread.currentThread().getName()+"："+entity);
            unsafe.compareAndSwapInt(entity,offset,unsafe.getIntVolatile(entity, offset),0);
            System.out.println(Thread.currentThread().getName()+"："+entity);
        });
        thread.start();
        thread1.start();

    }
}

class ABAEntity{
    int x;

    @Override
    public String toString() {
        return String.valueOf(x);
    }
}