package com.learning.fuelpricehistory.controllers;

import javax.validation.Valid;

import com.learning.fuelpricehistory.models.ApplicationUser;
import com.learning.fuelpricehistory.repositories.UserRepository;
import com.learning.fuelpricehistory.services.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@NoArgsConstructor
@RequestMapping("/users")
@Api(description = "Operations pertaining to user")
@Getter
@Setter
public class UserController extends GenericController<ApplicationUser, UserRepository, UserService> {

    @Override
    @ApiIgnore
    public ResponseEntity<ApplicationUser> create(@Valid ApplicationUser model) {
        return super.create(model);
    }

    @PostMapping("/sign-up")
    @ApiOperation(value = "Signup user")
    public void signUp(@Valid @RequestBody ApplicationUser user) {
        user.setPassword(super.bCryptPasswordEncoder.encode(user.getPassword()));
        super.getService().create(user);
    }

    @ApiOperation(value = "Find user by email")
    @ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @GetMapping("/search/email")
    public ResponseEntity<ApplicationUser> findByUsername(@Valid @RequestParam String email) {
        return ResponseEntity.ok(service.getRepository().findByUsername(email));
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<ApplicationUser> findAll(Pageable pageable) {
        return super.findAll(pageable);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApplicationUser> findById(@PathVariable Long id) {
        return super.findById(id);
    }

}
