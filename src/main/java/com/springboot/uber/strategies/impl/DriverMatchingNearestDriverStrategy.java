package com.springboot.uber.strategies.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.uber.entities.Driver;
import com.springboot.uber.entities.RideRequest;
import com.springboot.uber.repositories.DriverRepository;
import com.springboot.uber.strategies.DriverMatchingStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {
    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        return driverRepository.findTenNearestDriver(rideRequest.getPickUpLocation());
    }

}
