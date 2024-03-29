package io.demo.rpcfx.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.demo.rpcfx.api.RpcfxRequest;
import io.demo.rpcfx.api.RpcfxResolver;
import io.demo.rpcfx.api.RpcfxResponse;
import io.demo.rpcfx.exception.RpcfxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author Created by lx_068
 * @date 2021/12/30
 */
public class RpcfxInvoker {

    private final static Logger log = LoggerFactory.getLogger(RpcfxInvoker.class);

    private RpcfxResolver resolver;

    public RpcfxInvoker(RpcfxResolver resolver){
        this.resolver = resolver;
    }

    public RpcfxResponse invoke(RpcfxRequest request) {
        RpcfxResponse response = new RpcfxResponse();
        String serviceClass = request.getServiceClass();

        Class klass;
        try {
            klass = Class.forName(serviceClass);
        } catch (ClassNotFoundException e) {
            log.warn("{}",e);
            response.setException(new RpcfxException(e));
            response.setStatus(false);
            return response;
        }

        Object service = resolver.resole(klass);

        try {
            Method method = resolveMethodFromClass(service.getClass(), request.getMethod());
            Object result = method.invoke(service, request.getParams()); // dubbo, fastjson,
            // 两次json序列化能否合并成一个
            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
            response.setStatus(true);
            return response;
        } catch ( IllegalAccessException | InvocationTargetException e) {

            // 3.Xstream

            // 2.封装一个统一的RpcfxException
            // 客户端也需要判断异常
            e.printStackTrace();
            log.warn("{}",e);
            response.setException(new RpcfxException(e));
            response.setStatus(false);
            return response;
        }
    }

    private Class resolveClassFromString(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }

}
