package com.example.Voteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.example")
public class VoteServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoteServiceApplication.class, args);
	}

}
