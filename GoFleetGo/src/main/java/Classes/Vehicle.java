package Classes;

import MapClasses.Map;
import MapClasses.Position;
import Repositories.VehicleRepository;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class Vehicle {
    private int vehicleID;
    private int modelID;
    private int row;
    private int column;
    private VehicleStatus status;
    private float fuelLevel;
    private int distance;

    public enum VehicleStatus{
        free,
        inUse,
        reserved,
        disabled
    }
    //private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private ScheduledExecutorService executorService;
    private ScheduledFuture<?> driveTask;
    private ScheduledFuture<?> updateLocationTask;
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

    public int[] Drive() throws Exception {
        distance = 0;
        if (status == VehicleStatus.free) {
            status = VehicleStatus.inUse;
            VehicleRepository.updateStatus(vehicleID,status);

            executorService = Executors.newScheduledThreadPool(2);
            driveTask = executorService.scheduleAtFixedRate(this::MoveCarRandomly, 0, 1, TimeUnit.SECONDS);
            updateLocationTask = executorService.scheduleAtFixedRate(this::UpdateLocation, 0, 1, TimeUnit.SECONDS);
            return new int[]{row, column, distance};
        } else {
            throw new Exception("Vehicle is not free. Cannot start driving.");
        }
    }

    public void StopDrive() {
        if (status == VehicleStatus.inUse && driveTask != null && !driveTask.isCancelled()) {
            driveTask.cancel(true);
            status = VehicleStatus.free;
            VehicleRepository.updateStatus(vehicleID,status);
            executorService.shutdown();
        }
        if (updateLocationTask != null && !updateLocationTask.isCancelled()) {
            updateLocationTask.cancel(true);
            status = VehicleStatus.free;
            executorService.shutdown();
        }
    }

    private void UpdateLocation(){
        VehicleRepository.updateLocation(vehicleID, row, column);
    }

    private void MoveCarRandomly() {
        boolean[] possibleMoves = new boolean[4]; // 0: up, 1: down, 2: left, 3: right

        if ( !(row <= 0 || Map.getPositionType(row-1,column).equals(Position.EnumMapType.building)) ){
            possibleMoves[0] = true;
        } else if ( !(row >= Map.getMapSize() || Map.getPositionType(row+1,column).equals(Position.EnumMapType.building)) ){
            possibleMoves[1] = true;
        } else if ( !(column <= 0 || Map.getPositionType(row,column-1).equals(Position.EnumMapType.building)) ){
            possibleMoves[2] = true;
        } else if ( !(column >= Map.getMapSize() || Map.getPositionType(row,column+1).equals(Position.EnumMapType.building)) ){
            possibleMoves[3] = true;
        }

        Random random = new Random();
        int direction = random.nextInt(4);
       
        switch (direction) {
            case 0:
                if (possibleMoves[0]){
                    row--;
                    distance++;
                    break;
                }
                break;
            case 1:
                if (possibleMoves[1]){
                    row++;
                    distance++;
                    break;
                }
                break;
            case 2:
                if (possibleMoves[2]){
                    column--;
                    distance++;
                    break;
                } else if (possibleMoves[1]) {
                    row++;
                    distance++;
                    break;
                }
                break;
            case 3:
                if (possibleMoves[3]){
                    column++;
                    distance++;
                    break;
                } else if (possibleMoves[0]) {
                    row--;
                    distance++;
                    break;
                }
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

    public int getDistance(){
        return distance;
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

