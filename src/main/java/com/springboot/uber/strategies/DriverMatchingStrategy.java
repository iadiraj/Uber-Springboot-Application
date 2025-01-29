package com.springboot.uber.strategies;

import java.util.List;

import com.springboot.uber.entities.Driver;
import com.springboot.uber.entities.RideRequest;

public interface DriverMatchingStrategy {
    List<Driver> findMatchingDriver(RideRequest rideRequest);
}
