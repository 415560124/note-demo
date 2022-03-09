package com.rhy.note.thread.priority;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.T;

/**
 * @author: Rhy
 * @date: 2022/3/9 16:10
 * @description:
 */
public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new PriorityDemo());
        Thread thread2 = new Thread(new PriorityDemo());
        Thread thread3 = new Thread(new PriorityDemo());
        Thread thread4 = new Thread(new PriorityDemo());
        thread1.setPriority(Thread.MIN_PRIORITY);
        thread3.setPriority(Thread.MIN_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);
        thread4.setPriority(Thread.MAX_PRIORITY);
        System.out.println(thread1.getName());
        System.out.println(thread2.getName());
        System.out.println(thread3.getName());
        System.out.println(thread4.getName());
        thread2.start();
        thread1.start();
        thread3.start();
        thread4.start();
    }
}

class PriorityDemo implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName()+"：进行等待");
            Thread.yield();
            System.out.println(Thread.currentThread().getName()+"：开始执行");
        }
    }
}
