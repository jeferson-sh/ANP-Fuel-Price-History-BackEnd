package com.learning.fuelpricehistory.controllers;

import javax.validation.Valid;

import com.learning.fuelpricehistory.models.GenericModel;
import com.learning.fuelpricehistory.services.GenericService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public M create(@Valid @RequestBody M model) {
        return this.service.create(model);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")
    @ApiOperation(value = "View an entity by id")
    @ApiResponses({@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 404, message = "Id Entity not found")})
    @GetMapping("/{id}")
    public M findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")
    @ApiOperation(value = "Update an entity by id")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiResponses({@ApiResponse(code = 201, message = "Success"), @ApiResponse(code = 404, message = "Id Entity not found")})
    @PutMapping("/{id}")
    public M update(@PathVariable Long id, @Valid @RequestBody M model) {
       return this.service.update(id, model);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")
    @ApiOperation(value = "Delete an entity by id")
    @DeleteMapping("/{id}")
    @ApiResponses({@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 404, message = "Id Entity not found")})
    public void deleteById(@PathVariable Long id) {
        this.service.deleteById(id);
    }

}