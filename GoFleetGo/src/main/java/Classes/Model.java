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

    public Model(int modelID, String brand, double engine, double fuelCapacity, Date yearOfProduction) {
        this.modelID = modelID;
        this.brand = brand;
        this.engine = engine;
        this.fuelCapacity = fuelCapacity;
        this.yearOfProduction = yearOfProduction;
    }

    public static void ListAllModels(){
        ModelRepository modelRepository = new ModelRepository();
        List<Model> listOfModels = modelRepository.getAllDistinctModels();
        for (Model model : listOfModels) {
            System.out.println("Model ID: " + model.getModelID());
            System.out.println("Brand: " + model.getBrand());
            System.out.println("Engine: " + model.getEngine());
            System.out.println("Fuel Capacity: " + model.getFuelCapacity());
            System.out.println("Year of Production: " + model.getYearOfProduction());
            System.out.println("------------------------------");
        }
    }
    // Getters and setters

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

    @Override
    public String toString() {
        return "Model{" +
                "modelID=" + modelID +
                ", brand='" + brand + '\'' +
                ", engine=" + engine +
                ", fuelCapacity=" + fuelCapacity +
                ", yearOfProduction=" + yearOfProduction +
                '}';
    }
}
