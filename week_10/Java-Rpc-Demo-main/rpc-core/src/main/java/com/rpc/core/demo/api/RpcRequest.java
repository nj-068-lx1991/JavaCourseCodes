package com.rpc.core.demo.api;

import lombok.*;

/**
 * Rpc 自定义请求结构
 *
 * @author Created by lx_068
 * @date 2022/01/09
 */
@Data
public class RpcRequest {

    /**
     * 接口类名称
     */
    private String serviceClass;

    /**
     * 方法名
     */
    private String method;

    /**
     * 参数
     */
    private Object[] argv;

    private String group;

    private String version;
}
