package com.rpc.core.demo.balance.loadbalance;

import com.rpc.core.demo.api.ProviderInfo;

import java.util.List;
import java.util.TreeMap;

/**
 * 一致性哈希负载均衡器
 *
 * @author Created by lx_068
 * @date 2022/01/09
 */
public class ConsistentHashSelector {

    private final TreeMap<Long, String> virtualInvokers;
    private final int identityHashCode;

    ConsistentHashSelector(List<ProviderInfo> providers, int providersHashCode) {
        this.virtualInvokers = new TreeMap<>();
        this.identityHashCode = providersHashCode;

        for (ProviderInfo provider: providers) {
            String address = provider.getUrl();
            int replicaNumber = 1024;
            for (int i = 0; i < replicaNumber / 4; i++) {
                byte[] digest = (address + i).getBytes();
                for (int h = 0; h < 4; h++) {
                    long m = hash(digest, h);
                    virtualInvokers.put(m, provider.getUrl());
                }
            }
        }
    }

    int getIdentityHashCode() {
        return identityHashCode;
    }

    String select(String key) {
        byte[] digest = key.getBytes();
        return virtualInvokers.ceilingEntry(hash(digest, 0)).getValue();
    }

    private long hash(byte[] digest, int number) {
        return (((long) (digest[3 + number * 4] & 0xFF) << 24)
                | ((long) (digest[2 + number * 4] & 0xFF) << 16)
                | ((long) (digest[1 + number * 4] & 0xFF) << 8)
                | (digest[number * 4] & 0xFF))
                & 0xFFFFFFFFL;
    }
}
