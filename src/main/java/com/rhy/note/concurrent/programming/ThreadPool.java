package com.rhy.note.concurrent.programming;

import com.rhy.note.concurrent.programming.thread.ThreadPoolThread;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool {
    public static void main(String[] args) {
//        testRetry();
        //普通线程池
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,20,1, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(10));
//        for (int i = 0; i < 50; i++) {
//            threadPoolExecutor.execute(new ThreadPoolThread(String.valueOf(i+1)));
//        }
        //定时线程池
        ScheduledThreadPoolExecutor threadPoolExecutor = new ScheduledThreadPoolExecutor(10);
        //延迟两秒后执行一次
//        threadPoolExecutor.schedule(new Task(String.valueOf(1)),2,TimeUnit.MILLISECONDS);
        //上一个任务结束后延迟两秒后重复执行
//        threadPoolExecutor.scheduleWithFixedDelay(new Task(String.valueOf(1)),5000,2,TimeUnit.MILLISECONDS);
        threadPoolExecutor.scheduleAtFixedRate(new Task(String.valueOf(1)), 0, 1, TimeUnit.SECONDS);//任延迟取最大值 稳定定时器


    }

    private static void testRetry(){
        retry:
        for (int i = 0; i < 100; i++) {
            if(i == 50){
                System.out.println("retry");
                continue retry;
            }
//            if(i == 50){
//                System.out.println("retry");
//                break retry;
//            }
            System.out.println(i);
        }
        System.out.println("over");
    }
}


class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    AtomicInteger atomicInteger=new AtomicInteger();

    public void run() {
        atomicInteger.incrementAndGet();
//        if(true){
//
//            throw  new NullPointerException();
//        }
        System.out.println("Executing : " + name + ", Current Seconds : " + new Date().getSeconds());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}