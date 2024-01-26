package DTO;

import Classes.Vehicle;

import java.io.Serializable;
import java.time.LocalDateTime;

public class VehicleModelData implements Serializable {
    String brand;
    double enginge;
    double fuelLevel;
    LocalDateTime yearOfProduction;
    double range;
    Vehicle.VehicleStatus status;
    double price;

    public VehicleModelData(String brand, double enginge, double fuelLevel, LocalDateTime yearOfProduction, double range, Vehicle.VehicleStatus status, double price) {
        this.brand = brand;
        this.enginge = enginge;
        this.fuelLevel = fuelLevel;
        this.yearOfProduction = yearOfProduction;
        this.range = range;
        this.status = status;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getEnginge() {
        return enginge;
    }

    public void setEnginge(double enginge) {
        this.enginge = enginge;
    }

    public double getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(double fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public LocalDateTime getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(LocalDateTime yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public Vehicle.VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(Vehicle.VehicleStatus status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
