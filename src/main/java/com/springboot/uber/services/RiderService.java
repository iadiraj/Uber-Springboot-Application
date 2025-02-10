package com.springboot.uber.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.springboot.uber.dto.DriverDto;
import com.springboot.uber.dto.RideDto;
import com.springboot.uber.dto.RideRequestDto;
import com.springboot.uber.dto.RiderDto;
import com.springboot.uber.entities.Rider;
import com.springboot.uber.entities.User;

public interface RiderService {
    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    DriverDto rateDriver(Long rideId, Integer rating);

    RiderDto getMyProfile();

    Page<RideDto> getAllMyRides(PageRequest pageRequest);

    Rider createNewRider(User user);

    Rider getCurrentRider();
}
