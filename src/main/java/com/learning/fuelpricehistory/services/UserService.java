package com.learning.fuelpricehistory.services;

import com.learning.fuelpricehistory.models.ApplicationUser;
import com.learning.fuelpricehistory.repositories.UserRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<ApplicationUser, UserRepository> implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            ApplicationUser user = super.getRepository().findByUsername(username);
            System.err.println("USER: " + user);
            return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
        } catch (RuntimeException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}