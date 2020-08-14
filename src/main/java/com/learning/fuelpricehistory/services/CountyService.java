package com.learning.fuelpricehistory.services;

import com.learning.fuelpricehistory.models.County;
import com.learning.fuelpricehistory.repositories.CountyRepository;

import org.springframework.stereotype.Service;

@Service
public class CountyService extends GenericService<County, CountyRepository> {    
}