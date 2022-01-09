package com.rpc.demo.service;

import com.rpc.demo.model.Order;

/**
 * @author Created by lx_068
 * @date 2022/01/09
 */
public interface OrderService {

    /**
     * 根据id查询订单
     * @param id id
     * @return order
     */
    Order findById(Integer id);

    /**
     * 报错订单
     * @return exception
     */
    Order findError();
}
