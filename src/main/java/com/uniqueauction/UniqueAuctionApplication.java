package com.uniqueauction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UniqueAuctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniqueAuctionApplication.class, args);
	}

}
