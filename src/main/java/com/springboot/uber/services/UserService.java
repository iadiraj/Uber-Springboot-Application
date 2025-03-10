package com.springboot.uber.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.uber.entities.User;
import com.springboot.uber.exceptions.ResourceNotFoundException;
import com.springboot.uber.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public final class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElse(null);
    }

    public User getUserById(Long Id) {
        return userRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with Id" + Id));
    }

}
