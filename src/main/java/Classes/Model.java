package Classes;

import Repositories.ModelRepository;

import java.util.Date;
import java.util.List;

public class Model {
    private int modelID;
    private String brand;
    private double engine;
    private double fuelCapacity;
    private Date yearOfProduction;
    private double maxSpeed;



    private double price;
    public Model(int modelID, String brand, double engine, double fuelCapacity, Date yearOfProduction, double maxSpeed, double price) {
        this.modelID = modelID;
        this.brand = brand;
        this.engine = engine;
        this.fuelCapacity = fuelCapacity;
        this.yearOfProduction = yearOfProduction;
        this.maxSpeed = maxSpeed;
        this.price = price;
    }
    // Getters and setters

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public int getModelID() {
        return modelID;
    }

    public void setModelID(int modelID) {
        this.modelID = modelID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getEngine() {
        return engine;
    }

    public void setEngine(double engine) {
        this.engine = engine;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(double fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public Date getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Date yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "Model{" +
                "modelID=" + modelID +
                ", brand='" + brand + '\'' +
                ", engine=" + engine +
                ", fuelCapacity=" + fuelCapacity +
                ", yearOfProduction=" + yearOfProduction +
                ", maxSpeed=" + maxSpeed +
                '}';
    }
}
