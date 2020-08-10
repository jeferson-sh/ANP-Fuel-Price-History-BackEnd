package com.learning.fuelpricehistory.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface DateUtil {

	final String DATE_FORMAT = "dd/MM/yyyy";
	final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	static LocalDate getLocalDate(String dateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		// convert String to LocalDate
		return LocalDate.parse(dateString, formatter);
	}
}
