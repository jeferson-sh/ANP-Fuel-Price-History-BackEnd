package com.learning.fuelpricehistory.repositories;

import java.util.Optional;

import com.learning.fuelpricehistory.models.Banner;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends PagingAndSortingRepository<Banner, Long> {

    Optional<Banner> findByName(String name);

    Optional<Banner> findById(String name);

}
