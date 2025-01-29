package com.springboot.uber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.uber.entities.RideRequest;

public interface RideRequestRepository extends JpaRepository<RideRequest, Long> {

}
