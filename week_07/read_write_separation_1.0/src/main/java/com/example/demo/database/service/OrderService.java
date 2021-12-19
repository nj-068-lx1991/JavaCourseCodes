package com.example.demo.database.service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * 订单服务
 *
 * @author Created by lx_068
 * @date 2021/12/19
 */
public interface OrderService {

    void insertOne(DataSource dataSource, String sql);
    List<Map<String, Object>> query(DataSource dataSource, String sql);
}

