package com.mq.core.core.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Created by lx_068
 * @date 2022/01/30
 */
public class UUIDUtil {

    private static String localHost = null;

    public static String getUUID() {
        if (localHost == null) {
            localHost = getLocalHost();
        }
        return localHost + System.currentTimeMillis();
    }

    private static String getLocalHost() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
