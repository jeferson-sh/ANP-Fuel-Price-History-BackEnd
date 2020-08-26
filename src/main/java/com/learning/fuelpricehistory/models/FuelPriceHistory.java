package com.learning.fuelpricehistory.models;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Table(name = "fuels_prices")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FuelPriceHistory implements GenericModel<Long> {

    public static final String PK = "id";
    public static final String FK = "fuel_price_id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = PK)
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name=Banner.FK)
    @NotNull(message = "Banner can not be null")
    private Banner banner;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = County.FK)
    @NotNull(message = "County cannot be null")
    private County county;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = Region.FK)
    @NotNull(message = "Region cannot be null")
    private Region region;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = Reseller.FK)
    @NotNull(message = "Reseller cannot be null")
    private Reseller reseller;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = State.FK)
    @NotNull(message = "State cannot be null")
    private State state;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = Product.FK)
    @NotNull(message = "Product cannot be null")
    private Product product;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    private Double purchasePrice;

    private Double salePrice;

    private String measurementUnit;

    

}
