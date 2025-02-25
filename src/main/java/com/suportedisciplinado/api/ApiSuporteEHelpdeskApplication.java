package com.suportedisciplinado.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EntityScan("com.suportedisciplinado.api.model")
@EnableJpaRepositories("com.suportedisciplinado.api.repository")
public class ApiSuporteEHelpdeskApplication
{

	public static void main(String[] args) {
		SpringApplication.run(ApiSuporteEHelpdeskApplication.class, args);
	}

}
