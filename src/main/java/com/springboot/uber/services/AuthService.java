package com.springboot.uber.services;

import com.springboot.uber.dto.DriverDto;
import com.springboot.uber.dto.SingupDto;
import com.springboot.uber.dto.UserDto;

public interface AuthService {
    String login(String email, String password);

    UserDto signup(SingupDto singupDto);

    DriverDto onboardNewDriver(Long userId);

}
