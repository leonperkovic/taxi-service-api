package com.wearenotch.taxi.api.service;

import com.wearenotch.taxi.api.model.ScheduledRide;
import com.wearenotch.taxi.api.repository.ScheduledRideRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ScheduledRideService {
    private final ScheduledRideRepository scheduledRideRepository;
    private final TaxiDriverService taxiDriverService;

    public ScheduledRideService(ScheduledRideRepository scheduledRideRepository, TaxiDriverService taxiDriverService) {
        this.scheduledRideRepository = scheduledRideRepository;
        this.taxiDriverService = taxiDriverService;
    }

    public ScheduledRide getRide(UUID rideId) {
        return scheduledRideRepository.findById(rideId).orElseThrow(() -> new EntityNotFoundException("Cannot find ride with id: " + rideId));
    }

    public ScheduledRide saveRide(ScheduledRide scheduledRide) {
        return scheduledRideRepository.save(scheduledRide);
    }

    public void cancelRide(UUID rideId) {
        scheduledRideRepository.deleteById(rideId);
        // Notify client about cancellation
    }

    public void assignDriver(UUID rideId, UUID driverId) {
        var ride = getRide(rideId);
        ride.setDriver(taxiDriverService.getDriver(driverId));
        scheduledRideRepository.save(ride);
    }
}
