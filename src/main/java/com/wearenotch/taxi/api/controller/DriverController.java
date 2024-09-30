package com.wearenotch.taxi.api.controller;

import com.wearenotch.taxi.api.dto.RideRequestDto;
import com.wearenotch.taxi.api.dto.ScheduledRideDto;
import com.wearenotch.taxi.api.dto.TaxiDriverDto;
import com.wearenotch.taxi.api.service.ScheduledRideService;
import com.wearenotch.taxi.api.service.TaxiDriverService;
import com.wearenotch.taxi.api.utils.LocationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/drivers")
public class DriverController {
    private final TaxiDriverService taxiDriverService;
    private final ScheduledRideService scheduledRideService;

    public DriverController(TaxiDriverService taxiDriverService, ScheduledRideService scheduledRideService) {
        this.taxiDriverService = taxiDriverService;
        this.scheduledRideService = scheduledRideService;
    }

    @PostMapping(path = "/estimate-available")
    public ResponseEntity<List<TaxiDriverDto>> estimateAvailableDrivers(@RequestBody RideRequestDto rideRequestDto) {
        var estimatedDuration = LocationUtils.estimateDuration(rideRequestDto.getPickupLocation(), rideRequestDto.getDestination());
        var estimatedDropOffTime = rideRequestDto.getPickupTime().plus(estimatedDuration);
        var availableDrivers = taxiDriverService.getAvailableDrivers(rideRequestDto.getPickupTime(), estimatedDropOffTime)
                .stream()
                .map(TaxiDriverDto::fromModel)
                .toList();
        return ResponseEntity.ok(availableDrivers);
    }

    @PostMapping(path = "/{id}/schedule-ride")
    public ResponseEntity<ScheduledRideDto> scheduleRide(@PathVariable("id") UUID driverId, @RequestBody RideRequestDto rideRequestDto) {
        var scheduledRide = rideRequestDto.toScheduledRide();
        scheduledRide.setDriver(taxiDriverService.getDriver(driverId));
        var createdRide = scheduledRideService.saveRide(scheduledRide);
        return ResponseEntity.ok(ScheduledRideDto.fromModel(createdRide));
    }
}
