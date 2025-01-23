package com.joy.joyapi.order.config;


import com.joy.joyapi.order.domain.service.OrderService;
import com.joy.joyapi.order.domain.service.impl.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BootstrapConfig {

    @Bean
    OrderService orderService() {
        return new OrderServiceImpl();
    }
}
