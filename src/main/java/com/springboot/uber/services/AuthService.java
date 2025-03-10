package com.springboot.uber.services;

import com.springboot.uber.dto.DriverDto;
import com.springboot.uber.dto.SignupDto;
import com.springboot.uber.dto.UserDto;

public interface AuthService {
    String[] login(String email, String password);

    UserDto signup(SignupDto signupDto);

    DriverDto onboardNewDriver(Long userId, String vehicleId);

    String refreshToken(String refreshToken);

}
