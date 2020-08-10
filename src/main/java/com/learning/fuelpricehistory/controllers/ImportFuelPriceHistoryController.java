package com.learning.fuelpricehistory.controllers;

import java.net.URI;

import com.learning.fuelpricehistory.models.FuelPriceHistory;
import com.learning.fuelpricehistory.services.ImportColletionsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Api(description="Operations pertaining to import csv files")
@RestController
@RequestMapping("/fuelPriceHistory")
@Getter @Setter
@AllArgsConstructor
public class ImportFuelPriceHistoryController {

    @Autowired
    private ImportColletionsService service;

	@ApiOperation(value = "import Csv files")
    @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")
	@PostMapping(value = "/import")
	public ResponseEntity<Page<FuelPriceHistory>> importCsv(@RequestPart(value = "file") MultipartFile file) {
		service.importCsv(file);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.created(location).build();
	}

}