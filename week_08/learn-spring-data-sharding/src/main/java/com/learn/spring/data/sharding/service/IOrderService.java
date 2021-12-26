package com.learn.spring.data.sharding.service;

import com.learn.spring.data.sharding.domain.entity.Order;

import java.util.List;

/**
 * 订单业务层
 *
 * @author Creted by lx_068
 * @date 2021/12/26
 */
public interface IOrderService {

    /**
     * 插入订单数据
     * @return int
     */
    int insert(final Order order);

    /**
     * 查询订单
     * @return com.learn.spring.data.sharding.domain.entity.Order
     */
    Order selectById(final int id);

    /**
     * 查询全部订单
     *
     * @return java.util.List
     */
    List<Order> listAllOrders();

    /**
     * 查询全部订单数量
     *
     * @return java.lang.Integer
     */
    Integer countAllOrders();

    /**
     * 删除订单
     * @param id 根据id删除数据记录
     *
     */
    void deleteById(final int id);
}
