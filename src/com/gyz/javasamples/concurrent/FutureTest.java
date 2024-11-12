package com.gyz.javasamples.concurrent;

import java.util.concurrent.*;

/**
 * Created by guoyizhe on 2017/3/21.
 * 邮箱:gyzboy@126.com
 */

public class FutureTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "string";
            }
        };
        FutureTask<String> task = new FutureTask<>(callable);
        executor.submit(task);

        try {
            System.out.println(task.get());
        } catch (Exception e) {
        } finally {
            executor.shutdown();
        }
    }
}
