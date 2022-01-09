package com.rpc.core.demo.netty.common;

import lombok.*;

/**
 * Netty 通信的数据格式
 *
 * @author Created by lx_068
 * @date 2022/01/09
 */
@Data
public class RpcProtocol {

    /**
     * 数据大小
     */
    private int len;

    /**
     * 数据内容
     */
    private byte[] content;
}
