package com.joy.joyorchestrator;

import com.joy.joycommon.event.OutboxConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(OutboxConfig.class)
@EntityScan("com.joy.joyorchestrator")
public class JoyOrchestratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(JoyOrchestratorApplication.class, args);
	}

}
