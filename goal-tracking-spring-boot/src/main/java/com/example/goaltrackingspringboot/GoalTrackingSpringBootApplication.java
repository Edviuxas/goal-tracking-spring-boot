package com.example.goaltrackingspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class GoalTrackingSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoalTrackingSpringBootApplication.class, args);
	}

}
