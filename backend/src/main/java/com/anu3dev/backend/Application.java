package com.anu3dev.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

/*
 * @component - spring will take care of object creation
 *
 * @component and @bean
 * @controller and @RestController
 * @Service and @repository
 * @Configuration
 *
 *
 * @Override
 *
 */

/*
 * Just need to create DB by below command and table will be automatically created by JPA
 * create database full_stack_app;
 */