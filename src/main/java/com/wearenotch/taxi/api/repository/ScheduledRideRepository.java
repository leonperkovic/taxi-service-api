package com.wearenotch.taxi.api.repository;

import com.wearenotch.taxi.api.model.ScheduledRide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ScheduledRideRepository extends JpaRepository<ScheduledRide, UUID> {
}
