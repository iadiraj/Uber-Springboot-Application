package com.springboot.uber.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.springboot.uber.entities.Driver;
import com.springboot.uber.entities.Ride;
import com.springboot.uber.entities.RideRequest;
import com.springboot.uber.entities.Rider;
import com.springboot.uber.entities.enums.RideStatus;

public interface RideService {
    Ride getRideById(Long rideId);

    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);
}
