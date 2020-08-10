package com.learning.fuelpricehistory.repositories;

import java.time.LocalDate;
import com.learning.fuelpricehistory.models.FuelPriceHistory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelPriceHistoryRepository extends PagingAndSortingRepository<FuelPriceHistory, Long> {

	@Query(value = "SELECT AVG(fuel.purchasePrice) FROM FuelPriceHistory AS fuel INNER JOIN County AS cou ON fuel.county.id = cou.id AND cou.name=?1")
	Double findAverageFuelPurchasePriceBasedOnCountyName(String countyName);
	
	@Query(value = "SELECT AVG(fuel.salePrice) FROM FuelPriceHistory AS fuel INNER JOIN County AS cou ON fuel.county.id = cou.id AND cou.name=?1")
	Double findAverageFuelSalePriceBasedOnCountyName(String countyName);
	
	@Query(value = "SELECT fuel FROM FuelPriceHistory AS fuel INNER JOIN Region AS reg ON fuel.region.id = reg.id AND reg.name=?1")
	Page<FuelPriceHistory> findFuelPriceHistorysByRegionName(String string, Pageable pageable);
	
	@Query(value="SELECT fuel FROM FuelPriceHistory AS fuel INNER JOIN Reseller AS re ON fuel.reseller.id = re.id AND re.name=?1")
	Page<FuelPriceHistory> findFuelPriceHistorysByResellerName(String resellerName, Pageable pageable);
	
	Page<FuelPriceHistory> findByDate(LocalDate localDate, Pageable pageable);
	
	@Query(value = "SELECT AVG(fuel.salePrice) FROM FuelPriceHistory AS fuel INNER JOIN Banner AS ban ON fuel.county.id = ban.id AND ban.name=?1")
	Double findAverageFuelSalePriceBasedOnBannerName(String bannerName);

	@Query(value = "SELECT AVG(fuel.purchasePrice) FROM FuelPriceHistory AS fuel INNER JOIN Banner AS ban ON fuel.county.id = ban.id AND ban.name=?1")
	Double findAverageFuelPurchasePriceBasedOnBannerName(String bannerName);
}
