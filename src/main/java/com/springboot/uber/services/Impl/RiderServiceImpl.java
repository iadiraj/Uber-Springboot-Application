package com.springboot.uber.services.Impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.springboot.uber.dto.DriverDto;
import com.springboot.uber.dto.RideDto;
import com.springboot.uber.dto.RideRequestDto;
import com.springboot.uber.dto.RiderDto;
import com.springboot.uber.entities.Driver;
import com.springboot.uber.entities.Ride;
import com.springboot.uber.entities.RideRequest;
import com.springboot.uber.entities.Rider;
import com.springboot.uber.entities.User;
import com.springboot.uber.entities.enums.RideStatus;
import com.springboot.uber.entities.enums.RiderRequestStatus;
import com.springboot.uber.exceptions.ResourceNotFoundException;
import com.springboot.uber.repositories.RideRequestRepository;
import com.springboot.uber.repositories.RiderRepository;
import com.springboot.uber.services.DriverService;
import com.springboot.uber.services.RatingService;
import com.springboot.uber.services.RideService;
import com.springboot.uber.services.RiderService;
import com.springboot.uber.strategies.RideStrategyManager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideStrategyManager rideStrategyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final RideService rideService;
    private final DriverService driverService;
    private final RatingService ratingService;

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        Rider rider = getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRiderRequestStatus(RiderRequestStatus.PENDING);
        rideRequest.setRider(rider);
        Double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);
        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
        List<Driver> drivers = rideStrategyManager.driverMatchingStrategy(rider.getRating())
                .findMatchingDriver(rideRequest);
        // TODO: Send notification to all the drivers about this ride request
        return modelMapper.map(savedRideRequest, RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Rider rider = getCurrentRider();
        Ride ride = rideService.getRideById(rideId);
        if (!rider.equals(ride.getRider())) {
            throw new RuntimeException("Rider doesn't own this ride with id: " + rideId);
        }
        if (!ride.getRideStatus().equals(RideStatus.CONFIRMED)) {
            throw new RuntimeException("Ride cannot be cancelled, invalid status: " + ride.getRideStatus());
        }
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        driverService.updateDriverAvailability(ride.getDriver(), true);
        return modelMapper.map(savedRide, RideDto.class);
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        Ride ride = rideService.getRideById(rideId);
        Rider rider = getCurrentRider();
        if (!rider.equals(ride.getRider())) {
            throw new RuntimeException("Rider is not the owner of this Ride");
        }
        if (!ride.getRideStatus().equals(RideStatus.ENDED)) {
            throw new RuntimeException(
                    "Ride status is not ENDED hence cannot be rated, status: " + ride.getRideStatus());
        }
        return ratingService.rateDriver(ride, rating);
    }

    @Override
    public RiderDto getMyProfile() {
        Rider currentRider = getCurrentRider();
        return modelMapper.map(currentRider, RiderDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        Rider currentRider = getCurrentRider();
        return rideService.getAllRidesOfRider(currentRider, pageRequest).map(
                ride -> modelMapper.map(ride, RideDto.class));
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider.builder().user(user).rating(0.0).build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        // TODO : Implement Spring Security
        return riderRepository.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("Rider not found with Id: " + 1));
    }

}
