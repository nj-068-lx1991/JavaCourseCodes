package com.learn.spring.data.transaction.exception;

/**
 * 自定义回滚异常
 *
 * @author Create by lx_068
 * @date 2021/12/26
 */
public class RollbackException extends Exception {

    public RollbackException() {
        super();
    }

    public RollbackException(String message) {
        super(message);
    }

    public RollbackException(String message, Throwable cause) {
        super(message, cause);
    }

    public RollbackException(Throwable cause) {
        super(cause);
    }
}
