package com.learning.fuelpricehistory.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.learning.fuelpricehistory.models.State;

import java.util.Optional;

@Repository
public interface StateRepository extends PagingAndSortingRepository<State, Long> {

	Optional<State> findByUf(String string);

}
