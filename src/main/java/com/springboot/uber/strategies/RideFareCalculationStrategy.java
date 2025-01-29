package com.springboot.uber.strategies;

import com.springboot.uber.entities.RideRequest;

public interface RideFareCalculationStrategy {
    static final double RIDE_FARE_MULTIPLIER = 10;
    static final double SURGE_FACTOR = 2;

    double calculateFare(RideRequest rideRequest);
}
