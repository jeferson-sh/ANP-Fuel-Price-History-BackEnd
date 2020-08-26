package com.learning.fuelpricehistory.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Table(name = "counties")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class County implements GenericModel<Long> {

    public static final String PK = "id";

	public static final String FK = "county_id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = PK)
    private Long id;

    @NotNull(message = "Name can not be null")
    private String name;

}