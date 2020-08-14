package com.learning.fuelpricehistory.controllers;

import com.learning.fuelpricehistory.models.Banner;
import com.learning.fuelpricehistory.repositories.BannerRepository;
import com.learning.fuelpricehistory.services.BannerService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/banners")
@Api(description = "Operations pertaining to banner")
public class BannerController extends GenericController<Banner,BannerRepository, BannerService> {
}