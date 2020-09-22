package com.bestcommerce.services.impl;

import com.bestcommerce.entities.User;
import com.bestcommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException("User Not Found with username: " + s));

        return UserDetailsImpl.build(user);

    }
}
