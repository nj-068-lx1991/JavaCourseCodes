package com.rpc.core.demo.api;

import lombok.Data;

/**
 * Rpc 自定义响应结果
 *
 * @author Created by lx_068
 * @date 2022/01/09
 */
@Data
public class RpcResponse {

    /**
     * 响应结果
     */
    private Object result;

    /**
     * 函数是否执行成功
     */
    private Boolean status;

    /**
     * 执行失败的异常信息
     */
    private Exception exception;
}
