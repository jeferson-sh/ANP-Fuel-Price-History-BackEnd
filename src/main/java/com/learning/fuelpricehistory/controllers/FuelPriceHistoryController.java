package com.learning.fuelpricehistory.controllers;

import java.time.LocalDate;

import javax.validation.Valid;

import com.learning.fuelpricehistory.models.FuelPriceHistory;
import com.learning.fuelpricehistory.repositories.FuelPriceHistoryRepository;
import com.learning.fuelpricehistory.services.FuelPriceHistoryService;
import com.learning.fuelpricehistory.utils.DateUtil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/fuelsPricesHistory")
@Api(description = "Operations pertaining to fuel price")
@ApiImplicitParams({
		@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
public class FuelPriceHistoryController
		extends GenericController<FuelPriceHistory, FuelPriceHistoryRepository, FuelPriceHistoryService> {

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "View AVG purchase prices by county")
	@GetMapping("/avg/purchasePrice/county")
	public Double findAvgPurchasePriceCountyName(@RequestParam String county) {
		return this.service.getAveragePurchasePriceBasedOnCountyName(county);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "View AVG sales prices by county")
	@GetMapping("/avg/salesPrice/county")
	public Double findAvgSalesPriceCountyName(@RequestParam String county) {
		return this.service.getAverageSalesPriceBasedOnCounty(county);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "View AVG purchase prices and sales price by county")
	@GetMapping("/avg/purchaseAndSalesPrice/county")
	public Double findAvgPurchaseAndSalesPriceByCountyName(@RequestParam String county) {
		Double averageSalesPrice = this.findAvgSalesPriceCountyName(county);
		Double averagePurchasePrice = this.findAvgPurchasePriceCountyName(county);
		return averagePurchasePrice + averageSalesPrice / 2;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "View AVG purchase prices and sale prices by banner")
	@GetMapping("/avg/purchaseAndSalesPrice/banner")
	public Double findAvgPurchaseAndSalesPriceByBannerName(@RequestParam String banner) {
		Double averageSalesPrice = this.findAvgSalesPriceBannerName(banner);
		Double averagePurchasePrice = this.findAvgPurchasePriceBannerName(banner);
		return averagePurchasePrice + averageSalesPrice / 2;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "View AVG purchase prices by banner")
	@GetMapping("/avg/purchasePrice/banner")
	public Double findAvgPurchasePriceBannerName(@RequestParam String banner) {
		return this.service.getAveragePurchasePriceBasedOnBanner(banner);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "View AVG sale prices by banner")
	@GetMapping("/avg/salesPrice/banner")
	public Double findAvgSalesPriceBannerName(@RequestParam String banner) {
		return this.service.getAverageSalesPriceBasedOnBanner(banner);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "View  a list of fuel prices by region")
	@GetMapping("/search/region")
	public ResponseEntity<Page<FuelPriceHistory>> getFuelsPricesHistoriesByRegionName(@RequestParam @Valid String region,
			Pageable pageable) {
		return ResponseEntity.ok(this.service.getFuelPriceHistoryByRegionName(region, pageable));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "View  a list of fuel prices by reseller ")
	@GetMapping("/search/reseller")
	public Page<FuelPriceHistory> getFuelPriceHistoryByResellerName(@RequestParam String reseller, Pageable pageable) {
		return this.service.getRepository().findFuelPriceHistorysByResellerName(reseller, pageable);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@ApiOperation(value = "View  a list of fuel prices by date")
	@GetMapping("/search/date")
	public Page<FuelPriceHistory> getFuelPriceHistoryByDate(
			@RequestParam(name = "date", defaultValue = "yyyy-MM-dd") @DateTimeFormat(pattern = DateUtil.DATE_FORMAT, iso = DateTimeFormat.ISO.DATE) LocalDate date,
			Pageable pageable) {
		return this.service.getFuelPriceHistoryByDate(date, pageable);
	}
}
