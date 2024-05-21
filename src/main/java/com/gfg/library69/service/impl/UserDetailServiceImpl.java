package com.gfg.library69.service.impl;

import com.gfg.library69.domain.User;
import com.gfg.library69.exception.UserAlreadyExistsException;
import com.gfg.library69.repository.UserRepository;
import com.gfg.library69.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


@Service
public class UserDetailServiceImpl implements UserService {

    /*
    Create a user domain for creating a table in the databse
     Create the  user reposiroty
    Autowire here
    fetch user data from repository
     */




    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        else{
            throw  new UsernameNotFoundException("User not found");
        }

    }

    @Override
    public void addUser(User user) {


         Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if(optionalUser.isEmpty()){
            user.setAuthority("USER");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return;


        }
        throw new UserAlreadyExistsException("Username not available");

    }
}
