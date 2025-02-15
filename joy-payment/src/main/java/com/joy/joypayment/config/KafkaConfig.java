package com.joy.joypayment.config;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.BatchMessagingMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Configuration
public class KafkaConfig {
    @Bean
    StringJsonMessageConverter jsonMessageConverter() {
        return new StringJsonMessageConverter();
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(KafkaProperties props) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(consumerFactory(props));
        factory.setConcurrency(props.getListener().getConcurrency());
        factory.setRecordMessageConverter(jsonMessageConverter());
        factory.setBatchMessageConverter(new BatchMessagingMessageConverter(jsonMessageConverter()));
        return factory;
    }

    @Bean
    ConsumerFactory<String, String> consumerFactory(KafkaProperties props) {
        return new DefaultKafkaConsumerFactory<>(
                props.buildConsumerProperties(null),
                new StringDeserializer(),
                new StringDeserializer()
        );
    }
}
