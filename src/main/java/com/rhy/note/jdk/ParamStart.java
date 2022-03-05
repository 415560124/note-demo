package com.rhy.note.jdk;

import com.rhy.note.source.UseE;

import java.lang.reflect.Parameter;

/**
 * @author: Rhy
 * @date: 2021/11/16 14:29
 * @description:
 */
public class ParamStart {
    public void test(UseE useE){}
    public static void main(String[] args) throws NoSuchMethodException {
        final Class<ParamStart> paramStartClass = ParamStart.class;
        final Parameter[] tests = paramStartClass.getMethod("test", UseE.class).getParameters();
        for (Parameter test : tests) {
            System.out.println(test.getName());
        }
    }
}
