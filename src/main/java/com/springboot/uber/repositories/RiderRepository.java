package com.springboot.uber.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.uber.entities.Rider;
import com.springboot.uber.entities.User;

public interface RiderRepository extends JpaRepository<Rider, Long> {

    Optional<Rider> findByUser(User user);

}
