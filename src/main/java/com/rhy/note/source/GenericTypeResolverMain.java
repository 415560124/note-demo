package com.rhy.note.source;

import org.springframework.core.GenericTypeResolver;

import java.util.Arrays;

/**
 * @author: Herion Lemon
 * @date: 2021年04月19日 16:51:00
 * @slogan: 如果你想攀登高峰，切莫把彩虹当梯子
 * @description:
 */
public class GenericTypeResolverMain {
    public static void main(String[] args) {
        //一个泛型
//        System.out.println(GenericTypeResolver.resolveTypeArgument(DemoT.class,DemoParentT.class));
        //多个泛型
        Arrays.stream(GenericTypeResolver.resolveTypeArguments(DemoT.class,DemoParentT.class)).forEach(System.out::println);
        //获得返回类型
        Arrays.stream(DemoT.class.getDeclaredMethods()).forEach(method -> {
            System.out.println(GenericTypeResolver.resolveReturnType(method,DemoParentT.class));
        });
    }
}
