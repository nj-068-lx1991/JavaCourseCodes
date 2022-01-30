package com.mq.core.core.messagequeue;

import lombok.Data;

/**
 * @author Created by lx_068
 * @date 2022/01/30
 */
@Data
public class Message {

    private String topic;

    private String content;

    public Message(String topic, String content) {
        this.topic = topic;
        this.content = content;
    }
}
