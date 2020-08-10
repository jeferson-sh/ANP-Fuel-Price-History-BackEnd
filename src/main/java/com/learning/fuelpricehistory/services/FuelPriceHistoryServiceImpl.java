package com.learning.fuelpricehistory.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.learning.fuelpricehistory.models.FuelPriceHistory;
import com.learning.fuelpricehistory.repositories.FuelPriceHistoryRepository;
import com.learning.fuelpricehistory.utils.DateUtil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FuelPriceHistoryServiceImpl extends GenericService<FuelPriceHistory, FuelPriceHistoryRepository> {
    
    // Average Purchase Price Based On County Name
    public Double getAveragePurchasePriceBasedOnCountyName(String countyName) {
        return getRepository().findAverageFuelPurchasePriceBasedOnCountyName(countyName);
    }

    // Average Sale Price Based On County Name
    public Double getAverageSalesPriceBasedOnCounty(String countyName) {
        return getRepository().findAverageFuelSalePriceBasedOnCountyName(countyName);
    }

    // Average Sale Price Based On Banner Name
    public Double getAverageSalesPriceBasedOnBanner(String bannerName) {
        return getRepository().findAverageFuelSalePriceBasedOnBannerName(bannerName);
    }

    // Average Purchase Price Based On Banner Name
    public Double getAveragePurchasePriceBasedOnBanner(String bannerName) {
        return getRepository().findAverageFuelPurchasePriceBasedOnBannerName(bannerName);
    }

    // FuelPriceHistory By Region Name
    public Page<FuelPriceHistory> getFuelPriceHistoryByRegionName(String string, Pageable pageable) {
        return getRepository().findFuelPriceHistorysByRegionName(string, pageable);
    }

    public Page<FuelPriceHistory> getFuelPriceHistoryByDate(LocalDate date, Pageable pageable) {
        return getRepository().findByDate(
                DateUtil.getLocalDate(DateTimeFormatter.ofPattern(DateUtil.DATE_FORMAT).format(date)), pageable);
    }

}
