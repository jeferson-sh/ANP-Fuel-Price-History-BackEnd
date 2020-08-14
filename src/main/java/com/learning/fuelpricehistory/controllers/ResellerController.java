package com.learning.fuelpricehistory.controllers;

import com.learning.fuelpricehistory.models.Reseller;
import com.learning.fuelpricehistory.repositories.ResellerRepository;
import com.learning.fuelpricehistory.services.ResellerService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/resellers")
@Api(description = "Operations pertaining to reseller")
public class ResellerController extends GenericController<Reseller, ResellerRepository,ResellerService> {
    
}