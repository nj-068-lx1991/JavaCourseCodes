package io.demo.rpcfx.demo.consumer;

import io.demo.rpcfx.client.NettyRpcClient;
import io.demo.rpcfx.client.Rpcfx;
import io.demo.rpcfx.demo.api.Order;
import io.demo.rpcfx.demo.api.OrderService;
import io.demo.rpcfx.demo.api.User;
import io.demo.rpcfx.demo.api.UserService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Created by lx_068
 */
@SpringBootApplication
public class RpcfxClientApplication {

    public static void main(String[] args) {

        UserService userService = Rpcfx.create(UserService.class, "http://localhost:8080/");
        User user = userService.findById(1);
        System.out.println("find user id=1 from server: " + user.getName());

        OrderService orderService = Rpcfx.create(OrderService.class, "http://localhost:8080/");
        Order order = orderService.findOrderById(1992129);
        System.out.printf("find order name=%s, amount=%f%n", order.getName(), order.getAmount());

        NettyRpcClient.stop();
    }

}
