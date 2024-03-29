package com.rpc.core.demo.netty.client;

import com.alibaba.fastjson.JSON;
import com.rpc.core.demo.api.RpcResponse;
import com.rpc.core.demo.netty.common.RpcProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * 这里使用并发的等待-通知机制来拿到结果
 *
 * @author Created by lx_068
 * @date 2022/01/09
 */
@Slf4j
public class RpcClientSyncHandler extends SimpleChannelInboundHandler<RpcProtocol> {

    private CountDownLatch latch;
    private RpcResponse response;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcProtocol msg) {
        log.info("Netty client receive message:");
        log.info("Message length: " + msg.getLen());
        log.info("Message content: " + new String(msg.getContent(), CharsetUtil.UTF_8));

        // 将 RpcResponse字符串 反序列化成 RpcResponse对象
        String json = new String(msg.getContent(), CharsetUtil.UTF_8);
        RpcResponse rpcfxResponse = JSON.parseObject(json, RpcResponse.class);
        log.info("Netty client serializer : " + rpcfxResponse.toString());

        response = rpcfxResponse;
        latch.countDown();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 锁的初始化
     * @param latch CountDownLatch
     */
    void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    /**
     * 阻塞等待结果后返回
     * @return 后台服务器响应
     * @throws InterruptedException exception
     */
    RpcResponse getResponse() throws InterruptedException {
        latch.await();
        return response;
    }
}
