package com.geekuniversity.concurrency;

import java.util.concurrent.CompletableFuture;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * @author Created by lx_068
 * <p>
 * CompletableFuture 方式
 */
public class CompletableFutureMethod {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        int result = CompletableFuture.supplyAsync(CompletableFutureMethod::sum).join();

        // 确保 拿到result 并输出
        System.out.println("异步计算结果为: " + result);

        System.out.println("使用时间: " + (System.currentTimeMillis() - start) + Constants.MILLI_SECOND);

        // 然后退出main线程
    }

    /**
     * 斐波那契数列根据条件求和
     * @return int
     */
    private static int sum() {
        return fibonacci(Constants.FIBONACCI);
    }

    /**
     * 根据 a 值处理数据
     *
     * @param a 参数
     * @return int
     */
    private static int fibonacci(int a) {
        if (a < Constants.NUM_TWO) {
            return Constants.NUM_ONE;
        }
        return fibonacci(a - Constants.NUM_ONE) + fibonacci(a - Constants.NUM_TWO);
    }
}
