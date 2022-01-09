package com.rpc.core.demo.filter.server;

import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author Created by lx_068
 * @date 2022/01/09
 */
public class BackListFilter {

    private static ConcurrentSkipListSet backList = new ConcurrentSkipListSet();

    public static void addBackAddress(String address) {
        backList.add(address);
    }

    public static boolean checkAddress(String address) {
        return backList.contains(address);
    }
}
