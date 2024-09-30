package com.wearenotch.taxi.api.model;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Table
@Entity(name = "scheduled_ride")
public class ScheduledRide {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "client_name")
    private String clientName;
    @Column(name = "pickup_time")
    private OffsetDateTime pickupTime;
    @Column(name = "pickup_location")
    private String pickupLocation;
    @Column(name = "destination")
    private String destination;
    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private TaxiDriver driver;

    @PrePersist
    public void onCreate() {
        if (id == null) id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public OffsetDateTime getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(OffsetDateTime pickupTime) {
        this.pickupTime = pickupTime;
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

    public TaxiDriver getDriver() {
        return driver;
    }

    public void setDriver(TaxiDriver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "ScheduledRide{" +
               "id=" + id +
               ", clientName='" + clientName + '\'' +
               ", pickupTime=" + pickupTime +
               ", pickupLocation='" + pickupLocation + '\'' +
               ", destination='" + destination + '\'' +
               ", driver=" + driver +
               '}';
    }
}
