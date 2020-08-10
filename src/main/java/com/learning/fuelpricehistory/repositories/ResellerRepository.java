package com.learning.fuelpricehistory.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.learning.fuelpricehistory.models.Reseller;

@Repository
public interface ResellerRepository extends PagingAndSortingRepository<Reseller, Long> {

	Optional<Reseller> findByName(String name);

}
