package com.geekuniversity.concurrency;

import java.util.concurrent.*;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * @author Created by lx_068
 * <p>
 * FutureMethod 方式
 */
public class FutureMethod implements Callable<Long> {

    private long sum() {
        return fibonacci(Constants.FIBONACCI);
    }

    private long fibonacci(int a) {
        if (a < Constants.NUM_TWO) {
            return Constants.NUM_ONE;
        }
        return fibonacci(a - Constants.NUM_ONE) + fibonacci(a - Constants.NUM_TWO);
    }

    @Override
    public Long call() {
        return sum();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();
        // 创建线程，并调用method的sum()方法
        // 异步执行 下面方法
        ExecutorService exe = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10), r -> null);
        Future<Long> future = exe.submit(new FutureMethod());

        //这是得到的返回值
        long result = future.get();

        // 确保拿到result 并输出
        System.out.println("异步计算结果为: " + result);

        System.out.println("使用时间: " + (System.currentTimeMillis() - start) + Constants.MILLI_SECOND);

        // 然后退出main线程
        exe.shutdown();
    }
}
