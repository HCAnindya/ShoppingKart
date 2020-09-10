package com.shopping.kart.api.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ShoppingKartApiConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingKartApiConfigServerApplication.class, args);
	}

}
