package com.rpc.core.demo.filter.client;

import com.rpc.core.demo.api.ProviderInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by lx_068
 * @date 2022/01/09
 */
public class FilterLine {

    private static boolean isInit = false;
    private static List<RpcFilter> rpcFilters = new ArrayList<>();

    private static void init() {
        addFilter(new TagFilter());
    }

    public static void addFilter(RpcFilter filter) {
        rpcFilters.add(filter);
    }

    public static List<ProviderInfo> filter(List<ProviderInfo> providers, List<String> tags) {
        if (!isInit) {
            init();
            isInit = true;
        }

        List<ProviderInfo> filterResult = new ArrayList<>(providers);

        for (RpcFilter filter: rpcFilters) {
            filterResult = filter.filter(filterResult, tags);
        }

        System.out.printf("\n%s filter to %s\n", providers, filterResult);
        return filterResult;
    }
}
