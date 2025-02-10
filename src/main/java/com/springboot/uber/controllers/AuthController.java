package com.springboot.uber.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.uber.dto.DriverDto;
import com.springboot.uber.dto.OnBoardDriverDto;
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
    public ResponseEntity<UserDto> signUp(@RequestBody SingupDto singupDto) {
        return new ResponseEntity<>(authService.signup(singupDto), HttpStatus.CREATED);
    }

    @PostMapping(path = "onBoardNewDriver/{userId}")
    public ResponseEntity<DriverDto> onBoardNewDriver(@PathVariable(name = "userId") Long Id,
            @RequestBody OnBoardDriverDto onBoardDriverDto) {
        return new ResponseEntity<>(authService.onboardNewDriver(Id, onBoardDriverDto.getVehicleId()),
                HttpStatus.CREATED);
    }
}
