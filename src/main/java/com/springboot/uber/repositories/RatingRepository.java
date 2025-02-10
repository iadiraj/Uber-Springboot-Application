package com.springboot.uber.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.uber.entities.Driver;
import com.springboot.uber.entities.Rating;
import com.springboot.uber.entities.Ride;
import com.springboot.uber.entities.Rider;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByRider(Rider rider);

    List<Rating> findByDriver(Driver driver);

    Optional<Rating> findByRide(Ride ride);
}
