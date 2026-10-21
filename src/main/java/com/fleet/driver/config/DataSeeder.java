package com.fleet.driver.config;

import com.fleet.driver.model.Driver;
import com.fleet.driver.repository.DriverRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedDrivers(DriverRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Driver d = new Driver();
                d.setId("drv-552");
                d.setFullName("Elena Vance");
                d.setLicenseCategory("Commercial-A");
                d.setLicenseExpiration("2028-05-30");
                d.setSafetyScore(98.2);
                d.setSafetyCertifications("Manejo defensivo, Materiales peligrosos");
                d.setVehicleId("veh-001");
                d.setAssignmentId("asg-9982-x");
                repository.save(d);
            }
        };
    }
}
