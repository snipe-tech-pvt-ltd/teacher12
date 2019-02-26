package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
@EntityScan("com.example.demo1")
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.demo.Services"})
public class Demo9Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo9Application.class, args);
	}

}
