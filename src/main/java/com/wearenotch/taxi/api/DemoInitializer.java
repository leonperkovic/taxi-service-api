package com.wearenotch.taxi.api;

import com.wearenotch.taxi.api.model.TaxiDriver;
import com.wearenotch.taxi.api.repository.TaxiDriverRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DemoInitializer {
    private final TaxiDriverRepository taxiDriverRepository;

    public DemoInitializer(TaxiDriverRepository taxiDriverRepository) {
        this.taxiDriverRepository = taxiDriverRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadDemoData() {
        var drivers = List.of(
                new TaxiDriver(UUID.fromString("ea3d3a02-94ac-4a99-b11f-bdeb1270b6d3"), "John"),
                new TaxiDriver(UUID.fromString("fb6e744b-905a-44cd-b7bc-f11ba95800e2"), "Jane"),
                new TaxiDriver(UUID.fromString("4c6da669-f62c-44e9-b67d-371669a7f88f"), "Bob")
        );
        taxiDriverRepository.saveAll(drivers);
    }
}
