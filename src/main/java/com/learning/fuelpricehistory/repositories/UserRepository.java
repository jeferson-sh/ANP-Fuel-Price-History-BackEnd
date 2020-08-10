package com.learning.fuelpricehistory.repositories;

import com.learning.fuelpricehistory.models.ApplicationUser;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends PagingAndSortingRepository<ApplicationUser, Long> {

    ApplicationUser findByUsername(String email);

}
