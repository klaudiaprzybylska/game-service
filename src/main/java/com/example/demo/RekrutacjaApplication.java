package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class RekrutacjaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RekrutacjaApplication.class, args);
	}

	@Bean
	public Random random() {
		return new Random();
	}

}
