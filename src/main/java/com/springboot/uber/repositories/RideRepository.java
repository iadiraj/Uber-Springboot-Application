package com.springboot.uber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.uber.entities.Ride;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {

}
