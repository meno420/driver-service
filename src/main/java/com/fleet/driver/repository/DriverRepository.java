package com.fleet.driver.repository;

import com.fleet.driver.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, String> {

    Optional<Driver> findByVehicleId(String vehicleId);
}
