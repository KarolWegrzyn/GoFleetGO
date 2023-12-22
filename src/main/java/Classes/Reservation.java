package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Reservation {
    private int reservationID;
    private int userID;
    private int vehicleID;
    private Date startTime;

    public Reservation(int reservationID, int userID, int vehicleID, Date startTime) {
        this.reservationID = reservationID;
        this.userID = userID;
        this.vehicleID = vehicleID;
        this.startTime = startTime;
    }

    // Getters and setters

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationID=" + reservationID +
                ", userID=" + userID +
                ", vehicleID=" + vehicleID +
                ", startTime=" + startTime +
                '}';
    }
}

