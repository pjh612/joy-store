package com.joy.joyapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(com.joy.joycommon.logging.LoggingConfig.class)
public class LoggingConfig {
}
