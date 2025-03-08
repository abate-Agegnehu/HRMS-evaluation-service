package com.saas.discipline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients(basePackages = "com.saas.discipline.client")
public class DisciplineServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisciplineServiceApplication.class, args);
	}

}
