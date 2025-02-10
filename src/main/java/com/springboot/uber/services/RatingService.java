package com.springboot.uber.services;

import com.springboot.uber.dto.DriverDto;
import com.springboot.uber.dto.RiderDto;
import com.springboot.uber.entities.Ride;

public interface RatingService {
    DriverDto rateDriver(Ride ride, Integer rating);

    RiderDto rateRider(Ride ride, Integer rating);

    void CreateNewRating(Ride ride);
}
