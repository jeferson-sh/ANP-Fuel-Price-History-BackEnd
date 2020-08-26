package com.learning.fuelpricehistory.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
// @Table(name = "authorities")
public class Authority implements GenericModel<Long>, GrantedAuthority {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static final String PK = "id";
    public static final String FK = "authorities_id";

    @Id
    @Column(name = PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String authority;

    @ManyToMany(mappedBy = "authorities")
    Set<ApplicationUser> applicationUsers;

}