package com.learning.fuelpricehistory.services;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Getter;

@Getter
@Service
public class FindAndSaveService {

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

    private County saveCounty(String name) {
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

    public void saveAll(List<FuelPriceHistory> fuelsPriceHistory) {
        this.FuelsPricesHistoryRepository.saveAll(fuelsPriceHistory);
    }
}