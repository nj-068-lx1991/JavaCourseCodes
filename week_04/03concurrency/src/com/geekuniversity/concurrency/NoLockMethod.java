package com.geekuniversity.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * @author Created by lx_068
 * <p>
 * 无锁方式
 * 主要是通过不断循环查询返回值是否为空，来判断值是否已经计算完成
 */
public class NoLockMethod {

    private volatile Integer value = null;

    public void sum(int num) {
        value = fibonacci(num);
    }

    private int fibonacci(int a) {
        if (a < Constants.NUM_TWO) {
            return Constants.NUM_ONE;
        }
        return fibonacci(a - Constants.NUM_ONE) + fibonacci(a - Constants.NUM_TWO);
    }

    public int getValue() {
        while (value == null) {
            System.out.println("计算已完成:!");
        }
        return value;
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        final NoLockMethod method = new NoLockMethod();
        ExecutorService exe = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10), r -> null);
        exe.execute(() -> method.sum(Constants.FIBONACCI));

        //这是得到的返回值
        int result = method.getValue();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为: " + result);

        System.out.println("使用时间: " + (System.currentTimeMillis() - start) + Constants.MILLI_SECOND);

        // 然后退出main线程
    }
}
