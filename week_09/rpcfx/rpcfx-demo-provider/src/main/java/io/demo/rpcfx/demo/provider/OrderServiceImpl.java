package io.demo.rpcfx.demo.provider;

import io.demo.rpcfx.demo.api.Order;
import io.demo.rpcfx.demo.api.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author Created by lx_068
 * @date 2021/12/30
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Order findOrderById(int id) {
        return new Order(id, "Cuijing" + System.currentTimeMillis(), 9.9f);
    }
}