package com.rpc.core.demo.balance.loadbalance;

import com.rpc.core.demo.api.ProviderInfo;

import java.util.List;
import java.util.Random;

/**
 * 加权负载均衡
 *
 * @author Created by lx_068
 * @date 2022/01/09
 */
public class WeightBalance extends AbstractLoadBalance {

    public static final String NAME = "weight_balance";

    @Override
    public String select(List<ProviderInfo> providers, String serviceName, String methodName) {
        int totalWeight = 0;
        for (ProviderInfo provider: providers) {
            totalWeight += provider.getWeight();
        }

        int random = new Random().nextInt(totalWeight);
        System.out.printf("provider amount: %s, random : %d\n", providers.size(), random);
        for (ProviderInfo provider: providers) {
            random -= provider.getWeight();
            if (random <= 0) {
                return provider.getUrl();
            }
        }
        return providers.get(providers.size() - 1).getUrl();
    }
}
