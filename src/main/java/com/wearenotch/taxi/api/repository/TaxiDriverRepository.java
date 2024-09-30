package com.wearenotch.taxi.api.repository;

import com.wearenotch.taxi.api.model.TaxiDriver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaxiDriverRepository extends JpaRepository<TaxiDriver, UUID> {
}
