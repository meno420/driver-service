package com.fleet.driver.service;

import com.fleet.driver.model.Driver;
import com.fleet.driver.repository.DriverRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class DriverService {

    private final DriverRepository repository;

    public DriverService(DriverRepository repository) {
        this.repository = repository;
    }

    public List<Driver> findAll() {
        return repository.findAll();
    }

    public Driver findById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Conductor no encontrado: " + id));
    }

    public Driver findByVehicleId(String vehicleId) {
        return repository.findByVehicleId(vehicleId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No hay conductor asignado al vehículo: " + vehicleId));
    }

    public Driver create(Driver driver) {
        if (driver.getId() == null || driver.getId().isBlank()) {
            driver.setId("drv-" + UUID.randomUUID().toString().substring(0, 8));
        } else if (repository.existsById(driver.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Ya existe un conductor con id: " + driver.getId());
        }
        return repository.save(driver);
    }
}
