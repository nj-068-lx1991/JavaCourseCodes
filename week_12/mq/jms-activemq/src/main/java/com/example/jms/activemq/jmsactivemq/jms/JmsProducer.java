package com.example.jms.activemq.jmsactivemq.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Created by lx_068
 * @date 2022/01/23
 */
@Component
public class JmsProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(final String topic, final Map message) {
        jmsTemplate.convertAndSend(topic, message);
    }
}