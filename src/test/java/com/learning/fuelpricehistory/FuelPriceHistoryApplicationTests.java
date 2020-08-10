package com.learning.fuelpricehistory;

import com.learning.fuelpricehistory.services.BannerService;
import com.learning.fuelpricehistory.services.FuelPriceHistoryServiceImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FuelPriceHistoryApplicationTests {

	@Autowired
	BannerService bannerService;
	@Autowired
	FuelPriceHistoryServiceImpl priceService;

	@Test
	void contextLoads() {
	}

}
