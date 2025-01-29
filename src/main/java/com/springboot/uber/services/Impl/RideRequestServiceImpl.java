package com.springboot.uber.services.Impl;

import org.springframework.stereotype.Service;

import com.springboot.uber.entities.RideRequest;
import com.springboot.uber.exceptions.ResourceNotFoundException;
import com.springboot.uber.repositories.RideRequestRepository;
import com.springboot.uber.services.RideRequestService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {
    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId)
                .orElseThrow(() -> new ResourceNotFoundException("RideRequest not found with id: " + rideRequestId));
    }

    @Override
    public void update(RideRequest rideRequest) {
        rideRequestRepository.findById(rideRequest.getId()).orElseThrow(
                () -> new ResourceNotFoundException("RideRequest not found with id " + rideRequest.getId()));
        rideRequestRepository.save(rideRequest);
    }

}
