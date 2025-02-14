package com.joy.joycommon.logging;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class LoggingProducer {
    private final KafkaProducer<String, String> kafkaLoggingProducer;
    private final String topic;

    public LoggingProducer(KafkaProducer<String, String> kafkaLoggingProducer, String topic) {
        this.kafkaLoggingProducer = kafkaLoggingProducer;
        this.topic = topic;
    }

    public void sendMessage(String key, String value) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, value);
        kafkaLoggingProducer.send(producerRecord, (metadata, exception) -> {
            if (exception == null) {

            } else {
                exception.printStackTrace();
            }
        });
    }
}
