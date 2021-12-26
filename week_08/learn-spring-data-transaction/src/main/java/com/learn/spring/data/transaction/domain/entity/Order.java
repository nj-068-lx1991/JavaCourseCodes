package com.learn.spring.data.transaction.domain.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单实体类
 *
 * @author Created by lx_068
 * @date 2021/12/26
 */
@Data
public class Order {

    private Integer id;

    private String orderSn;

    private Integer customerId;

    private Short orderStatus;

    private Date createTime;

    private Date shipTime;

    private Date payTime;

    private Date receiveTime;

    private BigDecimal discountMoney;

    private BigDecimal shipMoney;

    private BigDecimal payMoney;

    private Short payMethod;

    private String address;

    private String receiveUser;

    private String shipSn;

    private String shipCompanyName;

}
