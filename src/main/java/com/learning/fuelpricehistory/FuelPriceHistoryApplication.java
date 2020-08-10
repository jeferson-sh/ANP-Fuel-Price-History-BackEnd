package com.learning.fuelpricehistory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FuelPriceHistoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FuelPriceHistoryApplication.class, args);
		System.out.println("ok");
	}

	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
