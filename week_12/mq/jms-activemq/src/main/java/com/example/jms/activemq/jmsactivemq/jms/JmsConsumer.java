package com.example.jms.activemq.jmsactivemq.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Created by lx_068
 * @date 2022/01/23
 */
@Component
public class JmsConsumer {

    @JmsListener(destination = "activeTest")
    public void receiveMessage(final Map message) {
        System.out.println(message.toString());
    }
}
