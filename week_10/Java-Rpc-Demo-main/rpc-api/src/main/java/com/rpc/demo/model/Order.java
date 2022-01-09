package com.rpc.demo.model;

import lombok.*;

/**
 * @author Created by lx_068
 * @date 2022/01/09
 */
@Data
@AllArgsConstructor
public class Order {

    private Integer id;
    private String name;
    private Integer userId;
}
