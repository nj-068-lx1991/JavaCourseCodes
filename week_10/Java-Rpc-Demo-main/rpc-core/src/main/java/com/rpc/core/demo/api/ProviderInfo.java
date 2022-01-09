package com.rpc.core.demo.api;

import lombok.*;

import java.util.List;

/**
 * @author Created by lx_068
 * @date 2022/01/09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProviderInfo {

    /**
     * Provider ID：ZK注册后会生成一个ID
     * Client 获取Provider列表时，将此ID设置为获取的ZK生成的ID
     */
    String id;

    /**
     * Provider对应的后端服务器地址
     */
    String url;

    /**
     * 标签：用于简单路由
     */
    List<String> tags;

    /**
     * 权重：用于加权负载均衡
     */
    Integer weight;
}
