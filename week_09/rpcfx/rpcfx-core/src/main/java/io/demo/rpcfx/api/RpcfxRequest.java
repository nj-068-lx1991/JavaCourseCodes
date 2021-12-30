package io.demo.rpcfx.api;

import lombok.Data;

/**
 * @author Created by lx_068
 * @date 2021/12/30
 */
@Data
public class RpcfxRequest {

    private String serviceClass;

    private String method;

    private Object[] params;
}
