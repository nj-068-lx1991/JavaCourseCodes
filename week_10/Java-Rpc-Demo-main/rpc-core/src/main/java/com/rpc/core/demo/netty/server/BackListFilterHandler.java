package com.rpc.core.demo.netty.server;

import com.alibaba.fastjson.JSON;
import com.rpc.core.demo.api.RpcResponse;
import com.rpc.core.demo.filter.server.BackListFilter;
import com.rpc.core.demo.netty.common.RpcProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * @author Created by lx_068
 * @date 2022/01/09
 */
public class BackListFilterHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        InetSocketAddress socket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = socket.getAddress().getHostAddress();
        System.out.println(clientIp);

        if (BackListFilter.checkAddress(clientIp)) {
            RpcResponse response = new RpcResponse();
            response.setStatus(false);
            response.setException(new Exception("back list"));

            RpcProtocol message = new RpcProtocol();
            String requestJson = JSON.toJSONString(response);
            message.setLen(requestJson.getBytes(CharsetUtil.UTF_8).length);
            message.setContent(requestJson.getBytes(CharsetUtil.UTF_8));

            ctx.channel().writeAndFlush(message).sync();
        }

        ctx.fireChannelRead(msg);
    }
}
