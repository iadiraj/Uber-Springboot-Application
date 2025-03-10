package com.springboot.uber.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.uber.dto.DriverDto;
import com.springboot.uber.dto.RatingDto;
import com.springboot.uber.dto.RideDto;
import com.springboot.uber.dto.RideStartDto;
import com.springboot.uber.dto.RiderDto;
import com.springboot.uber.services.DriverService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/drivers")
@RequiredArgsConstructor
@Secured("ROLE_DRIVER")
public class DriverController {
    private final DriverService driverService;

    @PostMapping(path = "/acceptRide/{rideRequestId}")
    public ResponseEntity<RideDto> acceptRide(@PathVariable(name = "rideRequestId") Long Id) {
        return ResponseEntity.ok(driverService.acceptRide(Id));
    }

    @PostMapping(path = "/startRide/{rideRequestId}")
    public ResponseEntity<RideDto> acceptRide(@PathVariable(name = "rideRequestId") Long Id,
            @RequestBody RideStartDto rideStartDto) {
        return ResponseEntity.ok(driverService.startRide(Id, rideStartDto.getOtp()));
    }

    @PostMapping(path = "/endRide/{rideId}")
    public ResponseEntity<RideDto> endRide(@PathVariable(name = "rideId") Long Id) {
        return ResponseEntity.ok(driverService.endRide(Id));
    }

    @PostMapping(path = "/cancelRide/{rideId}")
    public ResponseEntity<RideDto> cancelRide(@PathVariable(name = "rideId") Long Id) {
        return ResponseEntity.ok(driverService.cancelRide(Id));
    }

    @PostMapping(path = "/rateRider")
    public ResponseEntity<RiderDto> rateRider(@RequestBody RatingDto rateDto) {
        return ResponseEntity.ok(driverService.rateRider(rateDto.getRideId(), rateDto.getRating()));
    }

    @GetMapping(path = "/getMyProfile")
    public ResponseEntity<DriverDto> getMyProfile() {
        return ResponseEntity.ok(driverService.getMyProfile());
    }

    @GetMapping(path = "/getMyRides")
    public ResponseEntity<Page<RideDto>> getAllMyRides(@RequestParam(defaultValue = "0") Integer pageOffset,
            @RequestParam(defaultValue = "10", required = false) Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageOffset, pageSize, Sort.by(Direction.DESC, "createdTime", "id"));
        return ResponseEntity.ok(driverService.getAllMyRides(pageRequest));
    }

}
