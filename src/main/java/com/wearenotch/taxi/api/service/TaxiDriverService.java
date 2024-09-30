package com.wearenotch.taxi.api.service;

import com.wearenotch.taxi.api.model.ScheduledRide;
import com.wearenotch.taxi.api.model.TaxiDriver;
import com.wearenotch.taxi.api.repository.TaxiDriverRepository;
import com.wearenotch.taxi.api.utils.LocationUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class TaxiDriverService {
    private final TaxiDriverRepository taxiDriverRepository;

    public TaxiDriverService(TaxiDriverRepository taxiDriverRepository) {
        this.taxiDriverRepository = taxiDriverRepository;
    }

    public TaxiDriver getDriver(UUID driverId) {
        return taxiDriverRepository.findById(driverId)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find TaxiDriver with id " + driverId));
    }

    public List<TaxiDriver> getAvailableDrivers(OffsetDateTime startTime, OffsetDateTime endTime) {
        return taxiDriverRepository.findAll()
                .stream()
                .filter(driver -> noneMatchingTimeRange(driver.getScheduledRides(), startTime, endTime))
                .toList();
    }

    private boolean noneMatchingTimeRange(Collection<ScheduledRide> rides, OffsetDateTime startTime, OffsetDateTime endTime) {
        return rides.stream()
                .noneMatch(ride -> {
                    var estimatedDuration = LocationUtils.estimateDuration(ride.getPickupLocation(), ride.getDestination());
                    var estimatedDropOffTime = ride.getPickupTime().plus(estimatedDuration);
                    return isBetween(ride.getPickupTime(), startTime, endTime) || isBetween(estimatedDropOffTime, startTime, endTime);
                });
    }

    private boolean isBetween(OffsetDateTime time, OffsetDateTime startTime, OffsetDateTime endTime) {
        if (time.isEqual(startTime)) return true;
        if (time.isEqual(endTime)) return false;
        return time.isAfter(startTime) && time.isBefore(endTime);
    }
}
