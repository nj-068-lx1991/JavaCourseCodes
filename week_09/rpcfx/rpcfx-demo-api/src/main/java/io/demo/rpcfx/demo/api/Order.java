package io.demo.rpcfx.demo.api;

import lombok.*;

/**
 * @author Created by lx_068
 * @date 2021/12/30
 */
@Data
@AllArgsConstructor
public class Order {

    private int id;

    private String name;

    private float amount;

}
