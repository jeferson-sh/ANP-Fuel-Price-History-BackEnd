package com.learning.fuelpricehistory.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.learning.fuelpricehistory.models.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    Optional<Product> findByName(String name);

}
