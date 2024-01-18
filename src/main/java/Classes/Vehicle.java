package Classes;

import MapClasses.Position;
import Repositories.VehicleRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Vehicle {
    private int vehicleID;
    private int modelID;
    private int row;
    private int column;
    private VehicleStatus status;
    private float fuelLevel;

    public enum VehicleStatus{
        free,
        inUse,
        reserved,
        disabled
    }
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    public Vehicle(int vehicleID, int modelID, int row, int column, VehicleStatus status, float fuelLevel) {
        this.vehicleID = vehicleID;
        this.modelID = modelID;
        this.row = row;
        this.column = column;
        this.status = status;
        this.fuelLevel = fuelLevel;
    }

    public Vehicle(int vehicleID, int modelID, int row, int column, float fuelLevel) {
        this.vehicleID = vehicleID;
        this.modelID = modelID;
        this.row = row;
        this.column = column;
        this.status = VehicleStatus.free;
        this.fuelLevel = fuelLevel;
    }

    public int[] drive() throws Exception {
        if (status == VehicleStatus.free) {
            status = VehicleStatus.inUse;

            executorService.scheduleAtFixedRate(this::MoveCarRandomly, 0, 5, TimeUnit.SECONDS);
            return new int[]{row, column};
        } else {
            throw new Exception("Vehicle is not free. Cannot start driving.");
        }
    }

    private void MoveCarRandomly() {
        Random random = new Random();
        int direction = random.nextInt(4); // 0: up, 1: down, 2: left, 3: right

        switch (direction) {
            case 0:
                row--;
                break;
            case 1:
                row++;
                break;
            case 2:
                column--;
                break;
            case 3:
                column++;
                break;
        }

        System.out.println("Vehicle moved to Row: " + row + ", Column: " + column);
    }

    // Getters and setters
    public int getModelID() {
        return modelID;
    }

    public void setModelID(int modelID){
        this.modelID = modelID;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    public float getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(float fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleID=" + vehicleID +
                ", modelID=" + modelID +
                ", row='" + row +
                ", column='" + column +
                ", status='" + status +
                ", fuel level='" + fuelLevel + '\'' +
                '}';
    }
}

