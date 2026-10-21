package com.fleet.driver.controller;

import com.fleet.driver.model.Driver;
import com.fleet.driver.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService service;

    public DriverController(DriverService service) {
        this.service = service;
    }

    @GetMapping
    public List<Driver> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Driver getById(@PathVariable String id) {
        return service.findById(id);
    }

    @GetMapping("/by-vehicle/{vehicleId}")
    public Driver getByVehicle(@PathVariable String vehicleId) {
        return service.findByVehicleId(vehicleId);
    }

    @PostMapping
    public ResponseEntity<Driver> create(@Valid @RequestBody Driver driver) {
        Driver saved = service.create(driver);
        return ResponseEntity.created(URI.create("/drivers/" + saved.getId())).body(saved);
    }
}
