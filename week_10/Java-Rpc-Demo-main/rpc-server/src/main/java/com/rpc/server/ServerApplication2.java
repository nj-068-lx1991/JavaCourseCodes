package com.rpc.server;

import com.rpc.core.demo.netty.server.RpcNettyServer;
import com.rpc.core.demo.proxy.ProviderServiceManagement;

/**
 * @author Created by lx_068
 * @date 2022/01/09
 */
public class ServerApplication2 {

    public static void main(String[] args) throws Exception {
        int port = 8081;

        ProviderServiceManagement.init("com.rpc.server.demo.service.impl2", port);

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
