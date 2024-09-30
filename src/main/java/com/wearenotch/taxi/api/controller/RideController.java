package com.wearenotch.taxi.api.controller;

import com.wearenotch.taxi.api.dto.AssignDriverDto;
import com.wearenotch.taxi.api.dto.ScheduledRideDto;
import com.wearenotch.taxi.api.service.ScheduledRideService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/rides")
public class RideController {
    private final ScheduledRideService scheduledRideService;

    public RideController(ScheduledRideService scheduledRideService) {
        this.scheduledRideService = scheduledRideService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduledRideDto> getRide(@PathVariable UUID id) {
        var scheduledRide = scheduledRideService.getRide(id);
        return ResponseEntity.ok(ScheduledRideDto.fromModel(scheduledRide));
    }

    @PostMapping(path = "/{id}/cancel")
    public ResponseEntity<Void> cancelRide(@PathVariable("id") UUID rideId) {
        scheduledRideService.cancelRide(rideId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/{id}/assign")
    public ResponseEntity<Void> assignDriver(@PathVariable("id") UUID rideId, @RequestBody AssignDriverDto assignDriverDto) {
        scheduledRideService.assignDriver(rideId, assignDriverDto.getDriverId());
        return ResponseEntity.noContent().build();
    }
}
