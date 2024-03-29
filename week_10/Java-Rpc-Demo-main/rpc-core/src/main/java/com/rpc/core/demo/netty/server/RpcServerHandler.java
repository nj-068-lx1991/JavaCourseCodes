package com.rpc.core.demo.netty.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.rpc.core.demo.api.RpcRequest;
import com.rpc.core.demo.api.RpcResponse;
import com.rpc.core.demo.exception.CustomException;
import com.rpc.core.demo.netty.common.RpcProtocol;
import com.rpc.core.demo.proxy.ProviderServiceManagement;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Created by lx_068
 * @date 2022/01/09
 */

@Slf4j
public class RpcServerHandler extends SimpleChannelInboundHandler<RpcProtocol> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcProtocol msg) throws Exception {
        log.info("Netty server receive message:");
        log.info("Message length: " + msg.getLen());
        log.info("Message content: " + new String(msg.getContent(), CharsetUtil.UTF_8));

        // 获取 RpcProtocol中的 RpcRequest内容，反序列化成 RpcRequest 对象
        String json = new String(msg.getContent(), CharsetUtil.UTF_8);
        RpcRequest rpcRequest = JSON.parseObject(json, RpcRequest.class);
        log.info("Netty server serializer : " + rpcRequest.toString());

        // 获取相应的bean，反射调用方法，获取结果
        RpcResponse response = invoke(rpcRequest);

        // 返回结果给netty 客户端
        RpcProtocol message = new RpcProtocol();
        String requestJson = JSON.toJSONString(response);
        message.setLen(requestJson.getBytes(CharsetUtil.UTF_8).length);
        message.setContent(requestJson.getBytes(CharsetUtil.UTF_8));

        channelHandlerContext.writeAndFlush(message).sync();
        log.info("return response to client end");
    }

    /**
     * 获取接口实现对应的bean，反射调用方法，返回结果
     * @param request rpc request
     * @return result
     */
    private RpcResponse invoke(RpcRequest request) {
        RpcResponse response = new RpcResponse();

        log.info("service provider name: " + request.getServiceClass());
        Object service = ProviderServiceManagement.getProviderService(request);

        try {
            Method method = resolveMethodFromClass(service.getClass(), request.getMethod());
            Object result = method.invoke(service, request.getArgv());
            log.info("Server method invoke result: " + result.toString());
            // 两次json序列化能否合并成一个
            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
            response.setStatus(true);
            log.info("Server Response serialize to string return");
            return response;
        } catch (IllegalAccessException | InvocationTargetException | CustomException e) {
            e.printStackTrace();
            response.setException(e);
            response.setStatus(false);
            return response;
        }
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }
}
