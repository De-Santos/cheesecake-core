package com.user.sevice.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext run = SpringApplication.run(UserServiceApplication.class, args);
//		System.out.println(run.getEnvironment().getProperty("spring.datasource.username"));
//		System.out.println(run.getEnvironment().getProperty("spring.datasource.password"));
//		System.out.println(run.getEnvironment().getProperty("spring.datasource.url"));
	}

}
