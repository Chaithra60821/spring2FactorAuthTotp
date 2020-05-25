package com.example.spring2factor.spring2factorauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories (basePackageClasses = UserRepository.class)
public class Spring2factorauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(Spring2factorauthApplication.class, args);
	}

}
