package com.learning.fuelpricehistory;

import com.learning.fuelpricehistory.services.BannerService;
import com.learning.fuelpricehistory.services.FuelPriceHistoryService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FuelPriceHistoryApplicationTests {

	@Autowired
	BannerService bannerService;
	@Autowired
	FuelPriceHistoryService priceService;

	@Test
	void contextLoads() {
	}

}
