package com.kafka.example;

import com.kafka.example.kafka.KafkaUtil;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

/**
 * @author Created by lx_068
 * @date 2022/01/30
 */
public class Main {

    public static void main(String[] args) {
        String servers = "localhost:9092,localhost:9093,localhost:9094";
        String topic = "TestTopic";
        String message = "test";

        KafkaProducer<String, String> producer = KafkaUtil.createProducer(servers);
        KafkaUtil.send(producer, topic, message);

        KafkaConsumer<String, String> consumer = KafkaUtil.createConsumer(servers, topic);
        KafkaUtil.readMessage(consumer, 100);
    }
}