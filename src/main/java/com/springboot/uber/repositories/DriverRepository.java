package com.springboot.uber.repositories;

import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.uber.entities.Driver;
import com.springboot.uber.entities.User;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

        @Query(value = "SELECT d.*, ST_Distance(d.current_location, :pickupLocation) AS distance " +
                        "FROM driver AS d " +
                        "WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 10000) " +
                        "ORDER BY distance " +
                        "LIMIT 10", nativeQuery = true)
        List<Driver> findTenNearestDriver(@Param("pickupLocation") Point pickUpLocation);

        @Query(value = "SELECT d.* " +
                        "FROM driver d " +
                        "WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 15000) " +
                        "ORDER BY d.rating DESC " +
                        "LIMIT 10", nativeQuery = true)
        List<Driver> findTenNearByTopRatedDrivers(@Param("pickupLocation") Point pickUpLocation);

        Optional<Driver> findByUser(User user);
}
