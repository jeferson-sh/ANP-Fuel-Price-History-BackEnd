package com.learning.fuelpricehistory.controllers;

import com.learning.fuelpricehistory.models.Product;
import com.learning.fuelpricehistory.repositories.ProductRepository;
import com.learning.fuelpricehistory.services.ProductService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/products")
@Api(description = "Operations pertaining to product")
public class ProductController extends GenericController<Product,ProductRepository,ProductService> {
    
}