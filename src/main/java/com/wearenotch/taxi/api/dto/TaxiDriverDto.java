package com.wearenotch.taxi.api.dto;

import com.wearenotch.taxi.api.model.TaxiDriver;

import java.util.UUID;

public class TaxiDriverDto {
    private UUID id;
    private String name;

    public static TaxiDriverDto fromModel(TaxiDriver taxiDriver) {
        var dto = new TaxiDriverDto();
        dto.id = taxiDriver.getId();
        dto.name = taxiDriver.getName();
        return dto;
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

    @Override
    public String toString() {
        return "TaxiDriverDto{" +
               "id=" + id +
               ", name='" + name + '\'' +
               '}';
    }
}
