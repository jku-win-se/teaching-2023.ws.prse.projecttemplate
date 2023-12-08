package com.example.fahrtenbuch.entities;

public class Vehicle {
    private Integer vehicleId;
    private String licensePlate;
    private Double odometer;

    public Vehicle(String licensePlate, Double odometer) {
        this.licensePlate = licensePlate;
        this.odometer = odometer;
    }

    public Vehicle() {
        
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Double getOdometer() {
        return odometer;
    }

    public void setOdometer(Double odometer) {
        this.odometer = odometer;
    }

    public void setVehicleId(Integer vehicle_id) {
    }
}
