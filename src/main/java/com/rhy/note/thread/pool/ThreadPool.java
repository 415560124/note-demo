package com.rhy.note.thread.pool;

import java.util.concurrent.ScheduledThreadPoolExecutor;

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
//        threadPoolExecutor.scheduleAtFixedRate(new Task(String.valueOf(1)), 0, 1, TimeUnit.SECONDS);//任延迟取最大值 稳定定时器
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
