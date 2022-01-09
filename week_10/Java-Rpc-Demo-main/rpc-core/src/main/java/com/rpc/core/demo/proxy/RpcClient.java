package com.rpc.core.demo.proxy;

import com.google.common.base.Joiner;
import com.rpc.core.demo.balance.loadbalance.WeightBalance;
import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Created by lx_068
 * @date 2022/01/09
 */
public class RpcClient {

    private static String balanceAlgorithmName = WeightBalance.NAME;

    private ConcurrentHashMap<String, Object> proxyCache = new ConcurrentHashMap<>();

    public static void setBalanceAlgorithmName(String balanceAlgorithm) {
        balanceAlgorithmName = balanceAlgorithm;
    }

    public static String getBalanceAlgorithmName() {
        return balanceAlgorithmName;
    }

    private Object getProxy(String className) {
        return proxyCache.get(className);
    }

    private Boolean isExit(String className) {
        return proxyCache.containsKey(className);
    }

    private void add(String className, Object proxy) {
        proxyCache.put(className, proxy);
    }

    public <T> T create(Class<T> serviceClass) {
        String invoker = serviceClass.getName();
        if (!isExit(invoker)) {
            add(invoker, newProxy(serviceClass));
        }
        return (T) getProxy(invoker);
    }

    public <T> T create(Class<T> serviceClass, String group, String version) {
        String invoker = Joiner.on(":").join(serviceClass.getName(), group, version);
        if (!isExit(invoker)) {
            add(invoker, newProxy(serviceClass, group, version));
        }
        return (T) getProxy(invoker);
    }

    public <T> T create(Class<T> serviceClass, String group, String version, List<String> tags) {
        String invoker = Joiner.on(":").join(serviceClass.getName(), group, version, tags.toString());
        if (!isExit(invoker)) {
            add(invoker, newProxy(serviceClass, group, version, tags));
        }
        return (T) getProxy(invoker);
    }

    @SneakyThrows
    private <T> T newProxy(Class<T> serviceClass, String group, String version) {
        return (T) new ByteBuddy().subclass(Object.class)
                .implement(serviceClass)
                .intercept(InvocationHandlerAdapter.of(new RpcInvocationHandler(serviceClass, group, version)))
                .make()
                .load(RpcClient.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();
    }

    @SneakyThrows
    private <T> T newProxy(Class<T> serviceClass, String group, String version, List<String> tags) {
        return (T) new ByteBuddy().subclass(Object.class)
                .implement(serviceClass)
                .intercept(InvocationHandlerAdapter.of(new RpcInvocationHandler(serviceClass, group, version, tags)))
                .make()
                .load(RpcClient.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();
    }

    @SneakyThrows
    private <T> T newProxy(Class<T> serviceClass) {
        return (T) new ByteBuddy().subclass(Object.class)
                .implement(serviceClass)
                .intercept(InvocationHandlerAdapter.of(new RpcInvocationHandler(serviceClass)))
                .make()
                .load(RpcClient.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();
    }
}
