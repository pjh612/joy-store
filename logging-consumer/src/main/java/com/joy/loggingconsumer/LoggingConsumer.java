package com.joy.loggingconsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class LoggingConsumer {
    public LoggingConsumer(KafkaConsumer<String, String> kafkaLoggingConsumer) {
        Thread consumerThread = new Thread(() -> {
            try {
                while (true) {
                    ConsumerRecords<String, String> records = kafkaLoggingConsumer.poll(Duration.ofSeconds(1));
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.println("Received Message: " + record.value());
                    }
                }
            } catch (Exception e) {
                kafkaLoggingConsumer.close();
            }
        });
        consumerThread.start();
    }

}
