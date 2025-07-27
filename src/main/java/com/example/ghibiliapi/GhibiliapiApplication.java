package com.example.ghibiliapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GhibiliapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GhibiliapiApplication.class, args);
	}

}
