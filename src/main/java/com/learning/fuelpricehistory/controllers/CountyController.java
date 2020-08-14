package com.learning.fuelpricehistory.controllers;

import com.learning.fuelpricehistory.models.County;
import com.learning.fuelpricehistory.repositories.CountyRepository;
import com.learning.fuelpricehistory.services.CountyService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/counties")
@Api(description = "Operations pertaining to county")
public class CountyController extends GenericController<County,CountyRepository,CountyService> {
    
}