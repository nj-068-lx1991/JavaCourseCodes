package com.learn.spring.data.transaction.service;

import com.learn.spring.data.transaction.domain.entity.Order;
import com.learn.spring.data.transaction.exception.RollbackException;
import org.apache.shardingsphere.transaction.core.TransactionType;

import java.util.List;

/**
 * 订单服务
 *
 * @author ykthree
 * 2020/11/29
 */
public interface IOrderService {

    /**
     * 插入订单数据
     *
     * @param order 订单实体类
     * @return {@link TransactionType}
     */
    TransactionType insert(final Order order);

    /**
     * 测试分布式事务
     *
     * @param orders 订单实体类
     * @throws RollbackException 自定义异常
     */
    void insertThenRollback(List<Order> orders) throws RollbackException;

    /**
     * 根据id查询订单
     *
     * @param id 根据id查询订单记录
     * @return com.learn.spring.data.transaction.domain.entity.Order 订单实体类
     */
    Order selectById(final int id);

    /**
     * 查询全部订单
     *
     * @return 订单列表
     */
    List<Order> listAllOrders();

    /**
     * 查询全部订单数量
     *
     * @return java.lang.Integer 订单数量
     */
    Integer countAllOrders();

    /**
     * 删除订单
     *
     * @param id 根据id删除订单记录
     */
    void deleteById(final int id);

}
