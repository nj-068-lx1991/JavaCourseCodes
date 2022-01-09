package com.rpc.core.demo.balance.loadbalance;

import com.rpc.core.demo.api.ProviderInfo;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 一致性哈希 负载均衡
 *
 * @author Created by lx_068
 * @date 2022/01/09
 */
public class ConsistentHashBalance extends AbstractLoadBalance {

    public static final String NAME = "consistent_hash_balance";

    private final ConcurrentMap<String, ConsistentHashSelector> selectors = new ConcurrentHashMap<>();

    @Override
    public String select(List<ProviderInfo> providers, String serviceName, String methodName) {
        String key = serviceName + "." + methodName;
        int providersHashCode = providers.hashCode();

        ConsistentHashSelector selector = selectors.get(key);
        if (selector == null || selector.getIdentityHashCode() != providersHashCode) {
            selectors.put(key, new ConsistentHashSelector(providers, providersHashCode));
            selector = selectors.get(key);
        }
        return selector.select(key);
    }
}
