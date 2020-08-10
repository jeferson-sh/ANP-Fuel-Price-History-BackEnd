package com.learning.fuelpricehistory.repositories;

import com.learning.fuelpricehistory.models.Authority;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends PagingAndSortingRepository<Authority, Long> {
    
}