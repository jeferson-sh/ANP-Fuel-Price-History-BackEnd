package com.learning.fuelpricehistory.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.learning.fuelpricehistory.models.County;

@Repository
public interface CountyRepository extends PagingAndSortingRepository<County, Long> {
	
	Optional<County> findByName(String name);
	
	Optional<County> findById(String name);
}
