package io.demo.rpcfx.demo.provider;

import io.demo.rpcfx.api.RpcfxResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Created by lx_068
 * @date 2021/12/30
 */
public class DemoResolver implements RpcfxResolver, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public <T> T resole(Class<T> clazz) {
        return this.applicationContext.getBean(clazz);
    }
}
