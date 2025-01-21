package com.acme.springbootbootstrapproject;

import com.acme.springbootbootstrapproject.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.acme.springbootbootstrapproject.repository")
@Import(AppConfig.class)
public class SpringBootBootstrapProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBootstrapProjectApplication.class, args);
	}


}
