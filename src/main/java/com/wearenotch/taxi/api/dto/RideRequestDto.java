package com.wearenotch.taxi.api.dto;

import com.wearenotch.taxi.api.model.ScheduledRide;

import java.time.OffsetDateTime;

public class RideRequestDto {
    private String clientName;
    private OffsetDateTime pickupTime;
    private String pickupLocation;
    private String destination;

    public ScheduledRide toScheduledRide() {
        var scheduledRide = new ScheduledRide();
        scheduledRide.setClientName(clientName);
        scheduledRide.setPickupTime(pickupTime);
        scheduledRide.setPickupLocation(pickupLocation);
        scheduledRide.setDestination(destination);
        return scheduledRide;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public OffsetDateTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(OffsetDateTime pickupTime) {
        this.pickupTime = pickupTime;
    }

    @Override
    public String toString() {
        return "RideRequestDto{" +
               "clientName='" + clientName + '\'' +
               ", pickupLocation='" + pickupLocation + '\'' +
               ", destination='" + destination + '\'' +
               ", pickupTime=" + pickupTime +
               '}';
    }
}
