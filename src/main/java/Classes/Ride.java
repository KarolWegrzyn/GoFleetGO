package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ride {
    private int rideID;
    private int userID;
    private int vehicleID;
    private int reservationID;
    private int routeID;

    public Ride(int rideID, int userID, int vehicleID, int reservationID, int routeID) {
        this.rideID = rideID;
        this.userID = userID;
        this.vehicleID = vehicleID;
        this.reservationID = reservationID;
        this.routeID = routeID;
    }

    // Getters and setters

    public int getRideID() {
        return rideID;
    }

    public void setRideID(int rideID) {
        this.rideID = rideID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public int getRouteID() {
        return routeID;
    }

    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "rideID=" + rideID +
                ", userID=" + userID +
                ", vehicleID=" + vehicleID +
                ", reservationID=" + reservationID +
                ", routeID=" + routeID +
                '}';
    }
}

