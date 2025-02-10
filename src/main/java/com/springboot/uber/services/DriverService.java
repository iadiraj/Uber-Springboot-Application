package com.springboot.uber.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.springboot.uber.dto.DriverDto;
import com.springboot.uber.dto.RideDto;
import com.springboot.uber.dto.RiderDto;
import com.springboot.uber.entities.Driver;

public interface DriverService {
    RideDto acceptRide(Long rideRequestId);

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId, String otp);

    RideDto endRide(Long rideId);

    RiderDto rateRider(Long rideId, Integer rating);

    DriverDto getMyProfile();

    Page<RideDto> getAllMyRides(PageRequest pageRequest);

    Driver getCurrentDriver();

    Driver updateDriverAvailability(Driver driver, boolean available);

    Driver createNewDriver(Driver driver);
}
