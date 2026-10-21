package com.fleet.driver.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    private String id;

    @NotBlank
    @Column(nullable = false)
    private String fullName;

    @NotBlank
    @Column(nullable = false)
    private String licenseCategory;

    private String licenseExpiration;

    @DecimalMin("0.0")
    @DecimalMax("100.0")
    private Double safetyScore;

    private String safetyCertifications;

    private String vehicleId;
    private String assignmentId;

    public Driver() {
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getLicenseCategory() { return licenseCategory; }
    public void setLicenseCategory(String licenseCategory) { this.licenseCategory = licenseCategory; }

    public String getLicenseExpiration() { return licenseExpiration; }
    public void setLicenseExpiration(String licenseExpiration) { this.licenseExpiration = licenseExpiration; }

    public Double getSafetyScore() { return safetyScore; }
    public void setSafetyScore(Double safetyScore) { this.safetyScore = safetyScore; }

    public String getSafetyCertifications() { return safetyCertifications; }
    public void setSafetyCertifications(String safetyCertifications) { this.safetyCertifications = safetyCertifications; }

    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }

    public String getAssignmentId() { return assignmentId; }
    public void setAssignmentId(String assignmentId) { this.assignmentId = assignmentId; }
}
