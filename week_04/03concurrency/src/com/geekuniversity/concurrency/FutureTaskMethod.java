package com.geekuniversity.concurrency;

import java.util.concurrent.*;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * @author Created by lx_068
 * <p>
 * FutureTaskMethod 方式
 */
public class FutureTaskMethod {

    static class Get implements Callable<Integer> {
        FutureTask<Integer> sum;

        public Get(FutureTask<Integer> sum) {
            this.sum = sum;
        }

        @Override
        public Integer call() throws Exception {
            return sum.get();
        }
    }

    static class Sum implements Callable<Integer> {

        @Override
        public Integer call() {
            return fibonacci(Constants.FIBONACCI);
        }

        private int fibonacci(int a) {
            if (a < Constants.NUM_TWO) {
                return Constants.NUM_ONE;
            }
            return fibonacci(a - Constants.NUM_ONE) + fibonacci(a - Constants.NUM_TWO);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        FutureTask<Integer> sum = new FutureTask<>(new Sum());
        FutureTask<Integer> get = new FutureTask<>(new Get(sum));

        ExecutorService sumTask = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10), r -> null);
        sumTask.submit(sum);
        ExecutorService getTask = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10), r -> null);
        getTask.submit(get);
        //这是得到的返回值
        int result = get.get();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为: " + result);

        System.out.println("使用时间: " + (System.currentTimeMillis() - start) + Constants.MILLI_SECOND);

        // 然后退出main线程
        sumTask.shutdown();
        getTask.shutdown();
    }

    private static int sum() {
        return fibonacci(36);
    }

    private static int fibonacci(int a) {
        if (a < Constants.NUM_TWO) {
            return 1;
        }
        return fibonacci(a - Constants.NUM_ONE) + fibonacci(a - Constants.NUM_TWO);
    }

}
