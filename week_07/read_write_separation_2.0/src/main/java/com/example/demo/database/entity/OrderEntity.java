package com.example.demo.database.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Created by lx_068
 */
@Entity
@Data
public class OrderEntity implements Serializable {
    @Id @GeneratedValue
    private Integer order_id;
    private Integer order_code;
    private Integer customer_id;
    private String shipping_user;
    private Integer province;
    private Integer city;
    private Integer district;
    private String address;
    private Integer payment_method;
    private Double order_amount;
    private Double district_amount;
    private Double payment_amount;
    private Date pay_time;
    private Integer order_status;
    public OrderEntity(Integer order_id, Integer order_code, Double payment_amount) {
        this.order_id = order_id;
        this.order_code = order_code;
        this.payment_amount = payment_amount;
    }

    public OrderEntity() {

    }
}
