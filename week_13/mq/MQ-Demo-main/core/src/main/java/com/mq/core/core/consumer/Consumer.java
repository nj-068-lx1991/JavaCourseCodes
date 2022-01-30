package com.mq.core.core.consumer;

import java.util.List;

/**
 * @author Created by lx_068
 * @date 2022/01/30
 */
public interface Consumer {

    /**
     * 获取数据
     * 返回之多最大值 rate 的数据量
     * @param rate 最大数据量
     * @return data list
     */
    List poll(int rate);

}
