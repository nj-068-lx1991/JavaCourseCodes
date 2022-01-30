package com.mq.core.core.protocol.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author Created by lx_068
 * @date 2022/01/30
 */
public class ConsumerHandler implements WebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {

    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) {
        System.out.println(webSocketMessage.getPayload().toString());
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
