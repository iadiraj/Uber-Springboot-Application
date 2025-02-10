package com.springboot.uber.services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.springboot.uber.dto.DriverDto;
import com.springboot.uber.dto.RiderDto;
import com.springboot.uber.entities.Driver;
import com.springboot.uber.entities.Rating;
import com.springboot.uber.entities.Ride;
import com.springboot.uber.entities.Rider;
import com.springboot.uber.exceptions.ResourceNotFoundException;
import com.springboot.uber.exceptions.RuntimeConflictException;
import com.springboot.uber.repositories.DriverRepository;
import com.springboot.uber.repositories.RatingRepository;
import com.springboot.uber.repositories.RiderRepository;
import com.springboot.uber.services.RatingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final ModelMapper modelMapper;

    @Override
    public DriverDto rateDriver(Ride ride, Integer rating) {
        Driver driver = ride.getDriver();
        Rating ratingObj = ratingRepository.findByRide(ride)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found for ride with id: " + ride.getId()));
        if (ratingObj.getDriverRating() != null)
            throw new RuntimeConflictException("Driver has already been rated, cannot rate again");
        ratingObj.setDriverRating(rating);
        ratingRepository.save(ratingObj);
        Double newRating = ratingRepository.findByDriver(driver)
                .stream()
                .mapToDouble(rating1 -> rating1.getDriverRating())
                .average().orElse(0.0);
        driver.setRating(newRating);
        Driver savedDriver = driverRepository.save(driver);
        return modelMapper.map(savedDriver, DriverDto.class);
    }

    @Override
    public RiderDto rateRider(Ride ride, Integer rating) {
        Rider rider = ride.getRider();
        Rating ratingObj = ratingRepository.findByRide(ride)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found for ride with id: " + ride.getId()));
        if (ratingObj.getRiderRating() != null)
            throw new RuntimeConflictException("Rider has already been rated, cannot rate again");
        ratingObj.setRiderRating(rating);
        ratingRepository.save(ratingObj);
        Double newRating = ratingRepository.findByRider(rider)
                .stream()
                .mapToDouble(rating1 -> rating1.getRiderRating())
                .average().orElse(0.0);
        rider.setRating(newRating);
        Rider savedRider = riderRepository.save(rider);
        return modelMapper.map(savedRider, RiderDto.class);
    }

    @Override
    public void CreateNewRating(Ride ride) {
        Rating rating = Rating.builder()
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .ride(ride)
                .build();
        ratingRepository.save(rating);
    }

}
