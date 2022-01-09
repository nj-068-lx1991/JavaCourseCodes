package com.rpc.core.demo.filter.client;

import com.rpc.core.demo.api.ProviderInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by lx_068
 * @date 2022/01/09
 */
public class TagFilter implements RpcFilter {

    @Override
    public List<ProviderInfo> filter(List<ProviderInfo> providers, List<String> tags) {
        System.out.printf("\n%s tag filter start :: %s \n", tags.toString(), providers);
        if (tags.isEmpty()) {
            return providers;
        }

        List<ProviderInfo> newProviders = new ArrayList<>(providers.size());

        for (ProviderInfo provider: providers) {
            for (String tag: tags) {
                if (provider.getTags().contains(tag)) {
                    newProviders.add(provider);
                    break;
                }
            }
        }

        System.out.printf("\n 进行 Tag 简单路由转发: %s --> %s \n", providers.toString(), newProviders.toString());
        return newProviders;
    }
}
