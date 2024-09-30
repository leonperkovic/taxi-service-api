package com.wearenotch.taxi.api.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Table
@Entity(name = "taxi_driver")
public class TaxiDriver {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "driver")
    private List<ScheduledRide> scheduledRides;

    public TaxiDriver() {}

    public TaxiDriver(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ScheduledRide> getScheduledRides() {
        return scheduledRides;
    }

    public void setScheduledRides(List<ScheduledRide> scheduledRides) {
        this.scheduledRides = scheduledRides;
    }

    @Override
    public String toString() {
        return "TaxiDriver{" +
               "id=" + id +
               ", name='" + name + '\'' +
               '}';
    }
}
