package com.learning.fuelpricehistory.controllers;

import com.learning.fuelpricehistory.models.Region;
import com.learning.fuelpricehistory.repositories.RegionRepository;
import com.learning.fuelpricehistory.services.RegionService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/regions")
@Api(description = "Operations pertaining to region")
public class RegionController extends GenericController<Region, RegionRepository,RegionService> {}