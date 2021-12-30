package io.demo.rpcfx.api;

/**
 * @author Created by lx_068
 * @date 2021/12/30
 */
public interface RpcfxResolver {
    <T>T resole(Class<T> clazz);
}
