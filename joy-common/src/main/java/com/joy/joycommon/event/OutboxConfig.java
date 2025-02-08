package com.joy.joycommon.event;

import jakarta.persistence.EntityManager;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan("com.joy.joycommon.event")
public class OutboxConfig {

    @Bean
    OutboxEventDispatcher outboxEventDispatcher(EntityManager entityManager) {
        return new OutboxEventDispatcher(entityManager, false);
    }
}
