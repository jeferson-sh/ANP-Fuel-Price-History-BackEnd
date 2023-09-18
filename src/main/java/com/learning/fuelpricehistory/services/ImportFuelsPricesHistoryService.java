package com.learning.fuelpricehistory.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.learning.fuelpricehistory.models.FuelPriceHistory;
import com.learning.fuelpricehistory.utils.DateUtil;

import org.mozilla.universalchardet.UniversalDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImportFuelsPricesHistoryService {

	@Autowired
	private FindAndSaveService service;

	private final Logger logger = LoggerFactory.getLogger(ImportFuelsPricesHistoryService.class);

	// Import Data From Csv File
	public void importCsv(MultipartFile file) {
		this.service.saveAll(readCsv(file));
	}

	/**
	 * Row format: Region_initials - State_initials - County - Reseller -
	 * Reseller_CNPJ - Product - Date_collect - Sale_Price - Purchase_Price -
	 * Measurement_Unit - Banner
	 */
	public List<FuelPriceHistory> readCsv(MultipartFile multipartFile) {
		this.logger.info("Processing file...");
		String delimiter = ";";
		List<FuelPriceHistory> collects = new ArrayList<>();
		int invalidRecords = 0;
		Instant start = Instant.now();
		String[] columns = new String[0];
		try {
			// Reader reader = new
			// InputStreamReader(multipartFile.getInputStream(),Charset.forName("Cp1252"));
			Reader reader = new InputStreamReader(multipartFile.getInputStream(),
					UniversalDetector.detectCharset(multipartFile.getInputStream()));
			BufferedReader bufferedReader = new BufferedReader(reader);
			String readLine = "";
			// skip headers
			bufferedReader.readLine();
			while ((readLine = bufferedReader.readLine()) != null) {
				if ((columns = readLine.split(delimiter)).length == 11) {
					collects.add(createFuelPriceHistory(columns));
				} else {
					invalidRecords++;
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		} finally {
			Instant finish = Instant.now();
			this.logger.info("Processing time: " + (Duration.between(start, finish).toSeconds()) + " second(s)");
			this.logger.info("Invalid data registers: " + invalidRecords);
		}
		return collects;
	}

	private FuelPriceHistory createFuelPriceHistory(String[] columns) {
		FuelPriceHistory collect = new FuelPriceHistory();
		// Region
		collect.setRegion(service.findRegion(trim(columns[0])));
		// State
		collect.setState(service.findState(trim(columns[1])));
		// County
		collect.setCounty(service.findCounty(trim(columns[2])));
		// Reseller
		collect.setReseller(service.findReseller(trim(columns[3]), trim(columns[4])));
		// Product
		collect.setProduct(service.findProduct(trim(columns[5])));
		// Date
		collect.setRegistreDate(getDate(trim(columns[6])));
		// Sale price
		collect.setSalePrice(getPrice(trim(columns[7])));
		// Purchase price
		collect.setPurchasePrice(getPrice(trim(columns[8])));
		// Unit
		collect.setMeasurementUnit(trim(columns[9]));
		// Banner
		collect.setBanner(service.findBanner(trim(columns[10])));
		return collect;

	}

	private Double getPrice(String column) {
		return (column.isEmpty()) ? null : Double.parseDouble(removeInvalidCharacters(column));
	}

	private LocalDate getDate(String column) {
		return (column.isEmpty()) ? null : DateUtil.getLocalDate(removeInvalidCharacters(column));
	}

	private String removeInvalidCharacters(String column) {
		column = column.replaceAll(",", ".");
		column = column.replaceAll("^\"|\"$", "");
		return column;
	}

	private String trim(String column) {
		return column.trim();
	}
}