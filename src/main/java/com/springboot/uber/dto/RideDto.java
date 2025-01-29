package com.springboot.uber.dto;

import java.time.LocalDateTime;

import com.springboot.uber.entities.enums.PaymentMethod;
import com.springboot.uber.entities.enums.RideStatus;

import lombok.Data;

@Data
public class RideDto {
    private Long id;

    private PointDto pickUpLocation;

    private PointDto dropOffLocation;

    private LocalDateTime createdTime;

    private RiderDto rider;

    private DriverDto driver;

    private PaymentMethod paymentMethod;

    private RideStatus rideStatus;

    private String otp;

    private Double fare;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;
}
