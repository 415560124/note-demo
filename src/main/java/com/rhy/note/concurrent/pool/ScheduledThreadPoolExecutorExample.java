package com.rhy.note.concurrent.pool;


import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ScheduledThreadPoolExecutorExample {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(5);
        Task task = new Task("任务");
        System.out.println("Created : " + task.getName());
       // executor.schedule(task, 2, TimeUnit.SECONDS);
//        executor.scheduleWithFixedDelay(task, 0, 2, TimeUnit.SECONDS); //任务+延迟
        executor.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);//任延迟取最大值 稳定定时器

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
