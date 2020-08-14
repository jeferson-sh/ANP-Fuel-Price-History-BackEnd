package com.learning.fuelpricehistory.controllers;

import java.net.URI;

import javax.validation.Valid;

import com.learning.fuelpricehistory.models.GenericModel;
import com.learning.fuelpricehistory.services.GenericService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericController<M extends GenericModel<Long>, R extends PagingAndSortingRepository<M, Long>, S extends GenericService<M, R>> {

    @Autowired
    protected S service;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")
    @ApiOperation(value = "View a list")
    @GetMapping
    public Page<M> findAll(Pageable pageable) {
        return this.service.getRepository().findAll(pageable);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")
    @ApiOperation(value = "Create new entity")
    @PostMapping
    public ResponseEntity<M> create(@Valid @RequestBody M model) {
        M modelCreated = this.service.create(model);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(modelCreated.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")
    @ApiOperation(value = "View an entity by id")
    @GetMapping("/{id}")
    public ResponseEntity<M> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")
    @ApiOperation(value = "Update an entity by id")
    @PutMapping("/{id}")
    public ResponseEntity<M> update(@PathVariable Long id, @Valid @RequestBody M model) {
        M modelUpdate = this.service.update(id, model);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(modelUpdate.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")
    @ApiOperation(value = "Delete an entity by id")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        this.service.getRepository().deleteById(id);
    }

}