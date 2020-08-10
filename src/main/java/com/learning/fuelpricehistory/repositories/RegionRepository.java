package com.learning.fuelpricehistory.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.learning.fuelpricehistory.models.Region;

@Repository
public interface RegionRepository extends PagingAndSortingRepository<Region, Long> {
	
	Optional<Region> findByName(String name);
	
}
