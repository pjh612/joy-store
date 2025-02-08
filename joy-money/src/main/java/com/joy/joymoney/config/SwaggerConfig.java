package com.joy.joymoney.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ObjectUtils;

public class SwaggerConfig {
    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public OpenAPI openAPI(Info info) {
        if (ObjectUtils.isEmpty(info.getTitle())) {
            info.setTitle(this.applicationName);
        }
        if (ObjectUtils.isEmpty(info.getDescription())) {
            info.setDescription("");
        }

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }

    @Bean
    public Info info() {
        return new Info();
    }
}
