package com.learning.fuelpricehistory.services;

import java.util.List;

import com.learning.fuelpricehistory.models.FuelPriceHistory;
import com.learning.fuelpricehistory.repositories.BannerRepository;
import com.learning.fuelpricehistory.repositories.CountyRepository;
import com.learning.fuelpricehistory.repositories.FuelPriceHistoryRepository;
import com.learning.fuelpricehistory.repositories.ProductRepository;
import com.learning.fuelpricehistory.repositories.RegionRepository;
import com.learning.fuelpricehistory.repositories.ResellerRepository;
import com.learning.fuelpricehistory.repositories.StateRepository;
import com.learning.fuelpricehistory.utils.files.CollectsReaderUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImportColletionsService {

    @Autowired
    private FuelPriceHistoryRepository collectRepository;
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


    // Import Data From Csv File
    public void importCsv(MultipartFile file) {
        this.collectRepository.saveAll(getCollectionsFromCsv(file));
    }

    private List<FuelPriceHistory> getCollectionsFromCsv(MultipartFile file) {
        // String fileAsString = new String(file.getBytes(), StandardCharsets.UTF_8);
        return new CollectsReaderUtil(regionRepository, productRepository, bannerRepository, countyRepository,
                stateRepository, resellerRepository).readCsv(file);
    }
}