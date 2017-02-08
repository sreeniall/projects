package com.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bookstore.utils.StoreUtil;

@SpringBootApplication
public class SpringBootRestExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestExampleApplication.class, args);
	}

	@Bean
	public CommandLineRunner initializeDb(StoreUtil bookStoreUtil) {
		return (args) -> {
			//bookStoreUtil.cleanData();
			StoreUtil.prepareData();
		};
	}

}
