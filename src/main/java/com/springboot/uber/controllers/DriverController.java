package com.springboot.uber.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.uber.dto.RideDto;
import com.springboot.uber.dto.RideStartDto;
import com.springboot.uber.services.DriverService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/drivers")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @PostMapping(path = "/acceptRide/{rideRequestId}")
    public ResponseEntity<RideDto> acceptRide(@PathVariable(name = "rideRequestId") Long Id) {
        return ResponseEntity.ok(driverService.acceptRide(Id));
    }

    @PostMapping(path = "/startRide/{rideRequestId}")
    public ResponseEntity<RideDto> acceptRide(@PathVariable(name = "rideRequestId") Long Id,
            @RequestBody RideStartDto rideStartDto) {
        return ResponseEntity.ok(driverService.startRide(Id, rideStartDto.getOtp()));
    }
}
