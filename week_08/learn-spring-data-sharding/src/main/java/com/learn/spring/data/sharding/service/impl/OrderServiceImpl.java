package com.learn.spring.data.sharding.service.impl;

import com.learn.spring.data.sharding.domain.entity.Order;
import com.learn.spring.data.sharding.mapper.OrderMapper;
import com.learn.spring.data.sharding.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单业务实现类
 *
 * @author Created by lx_068
 * @date 2021/12/26
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper mapper;

    @Override
    public int insert(final Order order) {
        return mapper.insert(order);
    }

    @Override
    public Order selectById(final int id) {
        return mapper.selectById(id);
    }

    @Override
    public List<Order> listAllOrders() {
        return mapper.listAllOrders();
    }

    @Override
    public Integer countAllOrders() {
        return mapper.countAllOrders();
    }

    @Override
    public void deleteById(int id) {
        mapper.deleteById(id);
    }
}
