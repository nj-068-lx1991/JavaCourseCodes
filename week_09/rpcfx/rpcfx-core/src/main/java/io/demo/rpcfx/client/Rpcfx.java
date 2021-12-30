package io.demo.rpcfx.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import io.demo.rpcfx.api.RpcfxRequest;
import io.demo.rpcfx.api.RpcfxResponse;
import io.demo.rpcfx.exception.RpcfxException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.bytebuddy.matcher.ElementMatchers;
import okhttp3.MediaType;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Created by lx_068
 * @date 2021/12/30
 */
public final class Rpcfx {

    static {
        ParserConfig.getGlobalInstance().addAccept("io.kimmking");
    }

    private static final ConcurrentHashMap serviceObjectMap = new ConcurrentHashMap();

    public static <T> T create(final Class<T> serviceClass, final String url) {
        try {
            T reuslt = (T) serviceObjectMap.putIfAbsent(serviceClass, createByteBuddyDynamicProxy(serviceClass, url));
            if (reuslt == null) {
                // 第一次时会返回null，需要再获取一次。
                reuslt = (T) serviceObjectMap.get(serviceClass);
            }
            return reuslt;
        } catch (Exception e) {
            throw new RpcfxException(e);
        }
    }

    private static <T> T createByteBuddyDynamicProxy(Class<T> serviceClass, String url) throws Exception {
        return (T) new ByteBuddy().subclass(Object.class)
                .implement(serviceClass)
                .method(ElementMatchers.any())
                .intercept(InvocationHandlerAdapter.of(new Rpcfx.ServiceInvocationHandler(serviceClass, url)))
                .make()
                .load(Rpcfx.class.getClassLoader())
                .getLoaded()
                .getDeclaredConstructor()
                .newInstance();
    }

    public static class ServiceInvocationHandler implements InvocationHandler {
        public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");
        private final Class<?> serviceClass;
        private final String url;

        public ServiceInvocationHandler(Class<?> serviceClass, String url) {
            this.serviceClass = serviceClass;
            this.url = url;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
            RpcfxRequest request = new RpcfxRequest();
            request.setServiceClass(this.serviceClass.getName());
            request.setMethod(method.getName());
            request.setParams(params);

            // 使用netty client
            RpcfxResponse response = NettyRpcClient.rpcCall(request, url);
            if (!response.isStatus()) {
                throw new Throwable(response.getException());
            }
            return JSON.parse(response.getResult().toString());
        }
    }
}