package com.springboot.uber.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.uber.dto.SingupDto;
import com.springboot.uber.dto.UserDto;
import com.springboot.uber.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/singup")
    UserDto signUp(@RequestBody SingupDto singupDto) {
        return authService.signup(singupDto);
    }
}
