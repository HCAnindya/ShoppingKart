package com.shopping.kart.user.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ShoppingKartUserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingKartUserManagementApplication.class, args);
	}

}
