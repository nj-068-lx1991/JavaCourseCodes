package com.mq.core.core.producer;

/**
 * @author Created by lx_068
 * @date 2022/01/30
 */
public interface Producer {

    /**
     * Send message to broker
     * @param topic topic
     * @param message message
     * @return send success
     */
    boolean send(String topic, String message);
}
