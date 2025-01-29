package com.springboot.uber.services.Impl;

import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.uber.dto.DriverDto;
import com.springboot.uber.dto.SingupDto;
import com.springboot.uber.dto.UserDto;
import com.springboot.uber.entities.User;
import com.springboot.uber.entities.enums.Role;
import com.springboot.uber.exceptions.RuntimeConflictException;
import com.springboot.uber.repositories.UserRepository;
import com.springboot.uber.services.AuthService;
import com.springboot.uber.services.RiderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;

    @Override
    public String login(String email, String password) {
        return " ";
    }

    @Override
    @Transactional
    public UserDto signup(SingupDto signupDto) {
        User user = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
        if (user != null) {
            throw new RuntimeConflictException("Cannot Signup, User already exists with email" + signupDto.getEmail());
        }
        User mappedUser = modelMapper.map(signupDto, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(mappedUser);

        riderService.createNewRider(savedUser);
        // TODO - add walled related servie here

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }

}
