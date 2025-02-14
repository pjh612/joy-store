package com.joy.joycommon.logging;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class LoggingConfig {
    @Bean
    LoggingAspect loggingAspect(LoggingProducer loggingProducer) {
        return new LoggingAspect(loggingProducer);
    }

    @Bean("loggingProducer")
    LoggingProducer loggingProducer(@Value("${logging.topic}") String topic, KafkaProducer<String, String> kafkaLoggingProducer) {
        return new LoggingProducer(kafkaLoggingProducer, topic);
    }

    @Bean("kafkaLoggingProducer")
    KafkaProducer<String, String> kafkaLoggingProducer(@Value("${spring.kafka.bootstrap-servers}") String bootstrapServers) {
        Properties properties = new Properties();

        properties.put("bootstrap.servers", bootstrapServers);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        return new KafkaProducer<>(properties);
    }
}
