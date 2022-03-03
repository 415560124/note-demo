package com.rhy.note.service.impl;

import com.rhy.note.mvc.User;
import com.rhy.note.service.AAAService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author: Herion Lemon
 * @date: 2021/5/24 20:34
 * @slogan: 如果你想攀登高峰，切莫把彩虹当梯子
 * @description:
 */
@Service
public class AAAServiceImpl implements AAAService {
    @Override
    public void test() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        //
        //
        ///
        System.out.println(System.currentTimeMillis());
        //1~12
        Thread thread = new Thread(new AAAThread());
        thread.start();
        //
        //
        //
        System.out.println(System.currentTimeMillis());
    }
}
