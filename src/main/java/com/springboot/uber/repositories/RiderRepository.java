package com.springboot.uber.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.uber.entities.Rider;

public interface RiderRepository extends JpaRepository<Rider, Long> {

}
