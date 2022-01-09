package com.rpc.core.demo.balance;

import com.rpc.core.demo.api.ProviderInfo;

import java.util.List;

/**
 * @author Created by lx_068
 * @date 2022/01/09
 */
public interface LoadBalance {

    /**
     * 从当前Provider列表中，通过负载均衡，返回其中一个Provider的请求地址
     * @param providers provider list
     * @param serviceName service name
     * @param methodName method name
     * @return provider host url
     */
    String select(List<ProviderInfo> providers, String serviceName, String methodName);
}
