package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Vehicle {
    private int vehicleID;
    private int modelID;
    private String location;

    public Vehicle(int vehicleID, int modelID, String location) {
        this.vehicleID = vehicleID;
        this.modelID = modelID;
        this.location = location;
    }

    // Getters and setters

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getModelID() {
        return modelID;
    }

    public void setModelID(int modelID) {
        this.modelID = modelID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleID=" + vehicleID +
                ", modelID=" + modelID +
                ", location='" + location + '\'' +
                '}';
    }
}

