package com.learning.fuelpricehistory.controllers;

import com.learning.fuelpricehistory.models.State;
import com.learning.fuelpricehistory.repositories.StateRepository;
import com.learning.fuelpricehistory.services.StateService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;


@RestController
@RequestMapping("/states")
@Api(description = "Operations pertaining to state")
public class StateController extends GenericController<State,StateRepository,StateService > {
    
}