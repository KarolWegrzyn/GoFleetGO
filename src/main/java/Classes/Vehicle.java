package Classes;

import Repositories.VehicleRepository;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;


public class Vehicle {
    private Integer vehicleID;
    private int modelID;
    private double row;
    private double column;
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
    public Vehicle(int vehicleID, int modelID, double row, double column, VehicleStatus status, float fuelLevel) {
        this.vehicleID = vehicleID;
        this.modelID = modelID;
        this.row = row;
        this.column = column;
        this.status = status;
        this.fuelLevel = fuelLevel;
    }


//    public int[] Drive() throws Exception {
//        distance = 0;
//        if (status == VehicleStatus.free) {
//            status = VehicleStatus.inUse;
//            VehicleRepository.updateStatus(vehicleID,status);
//
//            executorService = Executors.newScheduledThreadPool(2);
//            driveTask = executorService.scheduleAtFixedRate(this::MoveCarRandomly, 0, 1, TimeUnit.SECONDS);
//            updateLocationTask = executorService.scheduleAtFixedRate(this::UpdateLocation, 0, 1, TimeUnit.SECONDS);
//            return new int[]{row, column, distance};
//        } else {
//            throw new Exception("Vehicle is not free. Cannot start driving.");
//        }
//    }
//
//    public void StopDrive() {
//        if (status == VehicleStatus.inUse && driveTask != null && !driveTask.isCancelled()) {
//            driveTask.cancel(true);
//            status = VehicleStatus.free;
//            VehicleRepository.updateStatus(vehicleID,status);
//            executorService.shutdown();
//        }
//        if (updateLocationTask != null && !updateLocationTask.isCancelled()) {
//            updateLocationTask.cancel(true);
//            status = VehicleStatus.free;
//            executorService.shutdown();
//        }
//    }

    private void UpdateLocation(){
        VehicleRepository.updateLocation(vehicleID, row, column);
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

    public double getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public double getColumn() {
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

