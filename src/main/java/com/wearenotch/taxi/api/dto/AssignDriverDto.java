package com.wearenotch.taxi.api.dto;

import java.util.UUID;

public class AssignDriverDto {
    private UUID driverId;

    public UUID getDriverId() {
        return driverId;
    }

    public void setDriverId(UUID driverId) {
        this.driverId = driverId;
    }

    @Override
    public String toString() {
        return "AssignDriverDto{" +
               "driverId='" + driverId + '\'' +
               '}';
    }
}
