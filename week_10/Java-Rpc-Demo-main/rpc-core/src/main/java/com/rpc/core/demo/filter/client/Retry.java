package com.rpc.core.demo.filter.client;

/**
 * @author Created by lx_068
 * @date 2022/01/09
 */
public class Retry {

    private static int retryLimit = 0;

    public static int getRetryLimit() {
        return retryLimit;
    }

    public static void setRetryLimit(int retryLimit) {
        Retry.retryLimit = retryLimit;
    }
}
