package com.joy.joypayment;

import com.joy.joycommon.event.OutboxConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(OutboxConfig.class)
@EntityScan("com.joy.joypayment")
public class JoyPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(JoyPaymentApplication.class, args);
    }

}
