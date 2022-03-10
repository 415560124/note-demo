package com.rhy.note.thread.cas;

import org.springframework.objenesis.instantiator.util.UnsafeUtils;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException {
        Entity entity = new Entity();
        Field x = entity.getClass().getDeclaredField("x");
        //通过class对象反射获取属性值
        Unsafe unsafe = UnsafeUtils.getUnsafe();
        //对象头8字节+对象指针4字节+当前字段类型字节数
        final long offset = unsafe.objectFieldOffset(x);
        //对象，偏移量，原值，新值
        unsafe.compareAndSwapInt(entity,offset,unsafe.getIntVolatile(entity,offset),1);
        System.out.println(entity);
    }
}

class Entity{
    int x;

    @Override
    public String toString() {
        return String.valueOf(x);
    }
}
