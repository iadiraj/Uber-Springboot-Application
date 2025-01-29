package com.springboot.uber.dto;

import java.time.LocalDateTime;

import com.springboot.uber.entities.Rider;
import com.springboot.uber.entities.enums.PaymentMethod;
import com.springboot.uber.entities.enums.RiderRequestStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDto {

    private Long id;

    private PointDto pickUpLocation;

    private PointDto dropOffLocation;

    private LocalDateTime requestedTime;

    private Rider rider;

    private Double fare;

    private PaymentMethod paymentMethod;

    private RiderRequestStatus riderRequestStatus;
}
