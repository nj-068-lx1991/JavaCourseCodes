package io.demo.rpcfx.exception;

/**
 * @author Created by lx_068
 * @date 2021/12/30
 */
public class RpcfxException extends RuntimeException{

    public RpcfxException() {
        super();
    }

    public RpcfxException(String message) {
        super(message);
    }

    public RpcfxException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcfxException(Throwable cause) {
        super(cause);
    }
}
