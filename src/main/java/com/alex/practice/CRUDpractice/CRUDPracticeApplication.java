package com.alex.practice.CRUDpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@SpringBootApplication
@ComponentScan(basePackages="com.alex.practice.CRUDpractice")
@EnableJpaRepositories("com.alex.practice.CRUDpractice.repository")
public class CRUDPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CRUDPracticeApplication.class, args);
	}
	@Bean
	public SpringDataDialect springDataDialect() {
		return new SpringDataDialect();
	}
}
