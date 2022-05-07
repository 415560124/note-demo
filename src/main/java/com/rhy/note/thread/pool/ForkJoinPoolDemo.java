package com.rhy.note.thread.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

@Slf4j
public class ForkJoinPoolDemo {
    public static final int LENGTH = 10000000;
    private static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();
    public static void main(String[] args) {
        int[] arr = createArray(100000000);
        //单线程运算
        singleSum(arr);
        //单线程分治
        recursiveSingleSum(arr);
        //forkjoin线程池运算，分治思想
        parallelSum(arr);
    }

    private static void recursiveSingleSum(int[] arr) {
        long start = System.currentTimeMillis();
        long total = recursiveSum(arr,0,arr.length);
        log.info("总计【{}】；单线程分治运算耗时【{}】",String.valueOf(total),String.valueOf(System.currentTimeMillis() - start));
    }

    private static long recursiveSum(int[] arr, int start, int length) {
        if(length - start > LENGTH){
            int middle = (start + length) / 2;
            long leftTotal = recursiveSum(arr,start,middle);
            long rightTotal = recursiveSum(arr,middle,length);
            return leftTotal+rightTotal;
        }
        long total = 0;
        for (int i = start; i < length; i++) {
            total += arr[i];
        }
        return total;
    }

    private static void parallelSum(int[] arr) {
        long start = System.currentTimeMillis();
        Long res = FORK_JOIN_POOL.invoke(new LongSum(arr, 0, arr.length));
        log.info("总计【{}】；分治多线程运算耗时【{}】",String.valueOf(res),String.valueOf(System.currentTimeMillis() - start));
    }

    private static void singleSum(int[] arr) {
        long start = System.currentTimeMillis();
        long total = 0;
        for (int i = 0; i < arr.length; i++) {
            total += arr[i];
        }
        log.info("总计【{}】；单线程运算耗时【{}】",String.valueOf(total),String.valueOf(System.currentTimeMillis() - start));
    }

    private static int[] createArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(100000);
        }
        return arr;
    }
}
@Slf4j
class LongSum extends RecursiveTask<Long> {
    int[] arr;
    int start;
    int length;

    public LongSum(int[] arr, int start, int length) {
        this.arr = arr;
        this.start = start;
        this.length = length;
    }

    @Override
    protected Long compute() {
        if(length - start > ForkJoinPoolDemo.LENGTH){
            int middle = (start + length) / 2;
            LongSum leftTask = new LongSum(arr,start,middle);
            LongSum rightTask = new LongSum(arr,middle,length);
            leftTask.fork();
            rightTask.fork();
            return leftTask.join()+rightTask.join();
        }
        long total = 0;
        for (int i = start; i < length; i++) {
            total += arr[i];
        }
        return total;
    }
}

