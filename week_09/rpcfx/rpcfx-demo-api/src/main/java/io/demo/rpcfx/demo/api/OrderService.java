package io.demo.rpcfx.demo.api;

/**
 * @author Created by lx_068
 * @date 2021/12/30
 */
public interface OrderService {

    /**
     * 根据id查询订单信息
     *
     * @param id 主键
     * @return io.demo.rpcfx.demo.api.Order
     */
    Order findOrderById(int id);
}
