package com.geekuniversity.filter;

import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

/**
 * @author Created by lx_068
 */
public class ClientProxyFilter implements HttpRequestFilter {
    private static final String URL_STR = "/hello";
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        String uri = fullRequest.uri();
        System.out.println(" filter 接收到的请求,url: " + uri);
        if (uri.startsWith(URL_STR)) {
            // 放过
            System.out.println("支持的url");
        } else {
            throw new RuntimeException("不支持的uri: " + uri);
        }
        HttpHeaders headers = fullRequest.headers();
        if (headers == null) {
            headers = new DefaultHttpHeaders();
        }
        headers.add("client-proxy: ", this.getClass().getSimpleName());
    }
}
