package com.springboot.uber.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.springboot.uber.dto.RideRequestDto;
import com.springboot.uber.entities.Driver;
import com.springboot.uber.entities.Ride;
import com.springboot.uber.entities.RideRequest;
import com.springboot.uber.entities.enums.RideStatus;

public interface RideService {
    Ride getRideById(Long rideId);

    void matchWithDrivers(RideRequestDto rideRequestDto);

    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Long driverId, PageRequest pageRequest);
}
