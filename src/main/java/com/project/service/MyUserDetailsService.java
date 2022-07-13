package com.project.service;

import com.project.UserPrinciple;
import com.project.model.User;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// When autowired, this class can help load user details for the configuration class.
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    // This method uses the autowired repository to load the user data from the database.
    // It does not need to be explicitly called, simply overriding and providing functionality is enough.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repository.findByUsername(username);

        if (user == null) {

            throw new UsernameNotFoundException("User 404");

        }

        // The UserDetails implementing class is how Spring Security knows how
        // to extract the username and password values from the User object.
        // It also has other settings that you can edit or enable/disable.
        return new UserPrinciple(user);

    }

}