package io.demo.rpcfx.api;

import lombok.Data;

/**
 * @author Created by lx_068
 * @date 2021/12/30.
 */
@Data
public class RpcfxResponse {

    private Object result;

    private boolean status;

    private Exception exception;
}
