package com.rpc.server;

import com.rpc.core.demo.filter.server.BackListFilter;
import com.rpc.core.demo.netty.server.RpcNettyServer;
import com.rpc.core.demo.proxy.ProviderServiceManagement;

/**
 * @author Created by lx_068
 * @date 2022/01/09
 */
public class ServerApplication {

    public static void main(String[] args) throws Exception {
        // 添加黑名单
        BackListFilter.addBackAddress("172.21.16.2");

        final int port = 8080;
        ProviderServiceManagement.init("com.rpc.server.demo.service.impl", port);

        final RpcNettyServer rpcNettyServer = new RpcNettyServer(port);

        try {
            rpcNettyServer.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rpcNettyServer.destroy();
        }
    }
}
