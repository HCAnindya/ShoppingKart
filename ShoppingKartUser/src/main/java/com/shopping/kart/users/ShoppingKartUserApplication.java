package com.shopping.kart.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ShoppingKartUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingKartUserApplication.class, args);
	}
}
