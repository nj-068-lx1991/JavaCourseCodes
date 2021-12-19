package com.example.demo.database.entity;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lx_068
 */
@Repository
public interface OrderMapper {

    /**
     * 插入一条
     * @param orderEntity 对象
     */
    void insertOne(OrderEntity orderEntity);

    /**
     * 插入多条
     * @param orders 订单
     */
    void insertMany(List<OrderEntity> orders);

    /**
     * 删除一条订单记录
     * @param id 主键
     */
    void delete(Integer id);

    /**
     * 修改记录
     * @param orderEntity 对象
     */
    void update(OrderEntity orderEntity);

    /**
     * 根据条件查询订单
     * @param condition 条件
     * @return java.util.List
     */
    List<Map<String, Object>> query(Map<String, Object> condition);
}
