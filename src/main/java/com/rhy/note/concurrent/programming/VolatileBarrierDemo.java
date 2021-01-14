package com.rhy.note.concurrent.programming;

/**
 * @author Rhy
 * @title volatile内存屏障示例
 * @description volatile内存屏障示例
 * @createTime 2020年12月25日 11:04:00
 * @modifier：Rhy
 * @modification_time：2020-12-25 11:04
 */
public class VolatileBarrierDemo {

    int a;
    volatile int v1 = 1;
    volatile int v2 = 2;
    void readAndWrite() {
        int i = v1;      //第一个volatile读
        int j = v2;      //第二个volatile读
        a = i + j;       //普通写
        v1 = i + 1;      //第一个volatile写
        v2 = j * 2;      //第二个 volatile写
    }
}
