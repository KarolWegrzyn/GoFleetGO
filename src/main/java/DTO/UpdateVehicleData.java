package DTO;

import java.io.Serializable;

public class UpdateVehicleData implements Serializable {

    private double distanceFromLastUpdate;
    private Integer vehicleId;
    double row;
    double column;

    public double getRow() {
        return row;
    }

    public void setRow(double row) {
        this.row = row;
    }

    public double getColumn() {
        return column;
    }

    public void setColumn(double column) {
        this.column = column;
    }

    public UpdateVehicleData() {}
    public double getDistanceFromLastUpdate() {
        return distanceFromLastUpdate;
    }

    public void setDistanceFromLastUpdate(double distanceFromLastUpdate) {
        this.distanceFromLastUpdate = distanceFromLastUpdate;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

}
