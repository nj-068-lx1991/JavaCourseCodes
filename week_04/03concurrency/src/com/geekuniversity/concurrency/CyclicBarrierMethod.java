package com.geekuniversity.concurrency;

import java.util.concurrent.*;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * @author Created by lx_068
 * <p>
 * CyclicBarrierMethod 方式
 */
public class CyclicBarrierMethod {

    private volatile Integer value = null;
    CyclicBarrier barrier;

    public void setBarrier(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public void sum(int num) throws BrokenBarrierException, InterruptedException {
        value = fibonacci(num);
        barrier.await();
    }

    private int fibonacci(int a) {
        if (a < 2) {
            return 1;
        }
        return fibonacci(a - 1) + fibonacci(a - 2);
    }

    public int getValue() throws InterruptedException {
        return value;
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        final CyclicBarrierMethod method = new CyclicBarrierMethod();
        CyclicBarrier barrier = new CyclicBarrier(Constants.NUM_ONE, () -> {
            //这是得到的返回值
            int result = Constants.NUM_ZERO;
            try {
                result = method.getValue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 确保  拿到result 并输出
            System.out.println("异步计算结果为: " + result);
            System.out.println("使用时间: " + (System.currentTimeMillis() - start) + Constants.MILLI_SECOND);
        });
        method.setBarrier(barrier);

        // 创建线程，并调用method的sum()方法
        ExecutorService exe = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10), r -> null);
        exe.execute(() -> {
            try {
                method.sum(Constants.FIBONACCI);
            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 然后退出main线程
    }
}
