package com.learning.fuelpricehistory.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
@Entity(name = "authorities")
public class Authority  implements GenericModel<Long>, GrantedAuthority{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static final String PK = "authority_id" ;

    @Id
    @Column(name = PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authority;
}