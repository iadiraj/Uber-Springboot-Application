package com.springboot.uber.strategies;

import java.time.LocalTime;

import org.springframework.stereotype.Component;

import com.springboot.uber.strategies.impl.DriverMatchingHighestRatedStrategy;
import com.springboot.uber.strategies.impl.DriverMatchingNearestDriverStrategy;
import com.springboot.uber.strategies.impl.RiderFareDefaultFareCalculationStrategy;
import com.springboot.uber.strategies.impl.RiderFareSurgePricingFareCalculationStrategy;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {
    private final DriverMatchingHighestRatedStrategy highestRatedDriverStrategy;
    private final DriverMatchingNearestDriverStrategy nearestDriverStrategy;
    private final RiderFareDefaultFareCalculationStrategy defaultFareCalculationStrategy;
    private final RiderFareSurgePricingFareCalculationStrategy surgePricingFareCalculationStrategy;

    public DriverMatchingStrategy driverMatchingStrategy(double riderRating) {
        if (riderRating >= 4.8) {
            return highestRatedDriverStrategy;
        }
        return nearestDriverStrategy;
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy() {
        LocalTime surgeStartTime = LocalTime.of(18, 0, 0);
        LocalTime surgeEndTime = LocalTime.of(21, 0, 0);
        LocalTime currentTime = LocalTime.now();
        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);
        if (isSurgeTime) {
            return surgePricingFareCalculationStrategy;
        }
        return defaultFareCalculationStrategy;
    }
}
