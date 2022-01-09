package com.rpc.core.demo.filter.client;

import com.rpc.core.demo.api.ProviderInfo;

import java.util.List;

/**
 * @author Created by lx_068
 * @date 2022/01/09
 */
public interface RpcFilter {

    List<ProviderInfo> filter(List<ProviderInfo> providers, List<String> tags);
}
