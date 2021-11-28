package com.geekuniversity.concurrency;

import java.util.concurrent.*;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * @author Created by lx_068
 * <p>
 * CountDownLatchMethod 方式
 */
public class CountDownLatchMethod {

    private volatile Integer value = null;
    private CountDownLatch latch;

    public void sum(int num) {
        value = fibonacci(num);
        latch.countDown();
    }

    private int fibonacci(int a) {
        if (a < Constants.NUM_TWO) {
            return Constants.NUM_ONE;
        }
        return fibonacci(a - Constants.NUM_ONE) + fibonacci(a - Constants.NUM_TWO);
    }

    public int getValue() throws InterruptedException {
        latch.await();
        return value;
    }

    /**
     * latch没有重置功能，用这个函数来传入新的
     *
     * @param latch 参数
     */
    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        CountDownLatch latch = new CountDownLatch(1);
        final CountDownLatchMethod method = new CountDownLatchMethod();
        method.setLatch(latch);

        // 线程池创建线程
        ExecutorService exe = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10), r -> null);
        exe.execute(() -> method.sum(Constants.FIBONACCI));
        //这是得到的返回值
        int result = method.getValue();
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + Constants.MILLI_SECOND);

        // 然后退出main线程
    }
}
