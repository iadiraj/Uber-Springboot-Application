package com.springboot.uber.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.uber.dto.DriverDto;
import com.springboot.uber.dto.RatingDto;
import com.springboot.uber.dto.RideDto;
import com.springboot.uber.dto.RideRequestDto;
import com.springboot.uber.dto.RiderDto;
import com.springboot.uber.services.RiderService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "/riders")
@RequiredArgsConstructor
public class RiderController {

    private final RiderService riderService;

    @PostMapping(path = "/requestRide")
    public ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto) {
        return ResponseEntity.ok(riderService.requestRide(rideRequestDto));
    }

    @PostMapping(path = "/cancelRide/{rideId}")
    public ResponseEntity<RideDto> cancelRide(@PathVariable(name = "rideId") Long Id) {
        return ResponseEntity.ok(riderService.cancelRide(Id));
    }

    @PostMapping(path = "/rateDriver")
    public ResponseEntity<DriverDto> rateDriver(@RequestBody RatingDto rateDto) {
        return ResponseEntity.ok(riderService.rateDriver(rateDto.getRideId(), rateDto.getRating()));
    }

    @GetMapping(path = "/getMyProfile")
    public ResponseEntity<RiderDto> getMyProfile() {
        return ResponseEntity.ok(riderService.getMyProfile());
    }

    @GetMapping(path = "/getMyRides")
    public ResponseEntity<Page<RideDto>> getAllMyRides(@RequestParam(defaultValue = "0") Integer pageOffset,
            @RequestParam(defaultValue = "10", required = false) Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageOffset, pageSize);
        return ResponseEntity.ok(riderService.getAllMyRides(pageRequest));
    }

    @PostMapping(path = "/rateDriver/{rideId}/{rating}")
    public ResponseEntity<DriverDto> rateRider(@PathVariable(name = "rideId") Long Id, @PathVariable Integer rating) {
        return ResponseEntity.ok(riderService.rateDriver(Id, rating));
    }

}
