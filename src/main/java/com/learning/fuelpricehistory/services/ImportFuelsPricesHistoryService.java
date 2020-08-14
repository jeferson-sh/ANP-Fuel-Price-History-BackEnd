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
import java.util.Optional;

import com.learning.fuelpricehistory.models.Banner;
import com.learning.fuelpricehistory.models.County;
import com.learning.fuelpricehistory.models.FuelPriceHistory;
import com.learning.fuelpricehistory.models.Product;
import com.learning.fuelpricehistory.models.Region;
import com.learning.fuelpricehistory.models.Reseller;
import com.learning.fuelpricehistory.models.State;
import com.learning.fuelpricehistory.repositories.BannerRepository;
import com.learning.fuelpricehistory.repositories.CountyRepository;
import com.learning.fuelpricehistory.repositories.FuelPriceHistoryRepository;
import com.learning.fuelpricehistory.repositories.ProductRepository;
import com.learning.fuelpricehistory.repositories.RegionRepository;
import com.learning.fuelpricehistory.repositories.ResellerRepository;
import com.learning.fuelpricehistory.repositories.StateRepository;
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
    private FuelPriceHistoryRepository FuelsPricesHistoryRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BannerRepository bannerRepository;
    @Autowired
    private CountyRepository countyRepository;
    @Autowired
    private ResellerRepository resellerRepository;
    @Autowired
    private StateRepository stateRepository;

	private final Logger logger = LoggerFactory.getLogger(ImportFuelsPricesHistoryService.class);



    // Import Data From Csv File
    public void importCsv(MultipartFile file) {
        this.FuelsPricesHistoryRepository.saveAll(readCsv(file));
    }

    /**
	 * Row format: Region_initials - State_initials - County - Reseller -
	 * Reseller_CNPJ - Product - Date_collect - Sale_Price - Purchase_Price -
	 * Measurement_Unit - Banner
	 */
	public List<FuelPriceHistory> readCsv(MultipartFile multipartFile) {
		this.logger.info("Processing file...");
		String delimiter = "\t";
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
		collect.setRegion(findRegion(trim(columns[0])));
		// State
		collect.setState(findState(trim(columns[1])));
		// County
		collect.setCounty(findCounty(trim(columns[2])));
		// Reseller
		collect.setReseller(findReseller(trim(columns[3]), trim(columns[4])));
		// Product
		collect.setProduct(findProduct(trim(columns[5])));
		// Date
		collect.setDate(getDate(trim(columns[6])));
		// Purchase price
		collect.setPurchasePrice(getPrice(trim(columns[7])));
		// Sale price
		collect.setSalePrice(getPrice(trim(columns[8])));
		// Unit
		collect.setMeasurementUnit(trim(columns[9]));
		// Banner
		collect.setBanner(findBanner(trim(columns[10])));
		return collect;

	}

	public Reseller findReseller(String name, String cnpj) {
		Optional<Reseller> reseller = this.resellerRepository.findByName(name);
		return (reseller.isPresent()) ? reseller.get() : saveReseller(name, cnpj);
	}

	private Reseller saveReseller(String name, String cnpj) {
		Reseller reseller = new Reseller();
		reseller.setName(name);
		reseller.setCnpj(cnpj);
		return this.resellerRepository.save(reseller);
	}

	public State findState(String stateInitial) {
		Optional<State> stateSaved = this.stateRepository.findByUf(stateInitial);
		return (stateSaved.isPresent()) ? stateSaved.get() : saveState(stateInitial);
	}

	private State saveState(String stateInitials) {
		State stateSaved = new State();
		stateSaved.setUf(stateInitials);
		return this.stateRepository.save(stateSaved);
	}

	public Region findRegion(String name) {
		Optional<Region> regionSaved = this.regionRepository.findByName(name);
		return (regionSaved.isPresent()) ? regionSaved.get() : saveRegion(name);
	}

	private Region saveRegion(String name) {
		Region regionSaved = new Region();
		regionSaved.setName(name);
		return this.regionRepository.save(regionSaved);
	}

	public County findCounty(String countyName) {
		Optional<County> countySaved = this.countyRepository.findByName(countyName);
		return (countySaved.isPresent()) ? countySaved.get() : saveCounty(countyName);
	}

	public County saveCounty(String name) {
		County countySaved = new County();
		countySaved.setName(name);
		return this.countyRepository.save(countySaved);
	}

	public Product findProduct(String name) {
		Optional<Product> productSaved = productRepository.findByName(name);
		return (productSaved.isPresent()) ? productSaved.get() : saveProduct(name);
	}

	private Product saveProduct(String name) {
		Product product = new Product();
		product.setName(name);
		return this.productRepository.save(product);
	}

	public Banner findBanner(String name) {
		Optional<Banner> bannerSaved = bannerRepository.findByName(name);
		return (bannerSaved.isPresent()) ? bannerSaved.get() : saveBanner(name);
	}

	private Banner saveBanner(String name) {
		Banner banner = new Banner();
		banner.setName(name);
		return this.bannerRepository.save(banner);
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