package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Transaction {
    private int transactionID;
    private int userID;
    private int vehicleID;
    private int rideID;
    private Date date;

    public Transaction(int transactionID, int userID, int vehicleID, int rideID, Date date) {
        this.transactionID = transactionID;
        this.userID = userID;
        this.vehicleID = vehicleID;
        this.rideID = rideID;
        this.date = date;
    }

    // Getters and setters

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
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

    public int getRideID() {
        return rideID;
    }

    public void setRideID(int rideID) {
        this.rideID = rideID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", userID=" + userID +
                ", vehicleID=" + vehicleID +
                ", rideID=" + rideID +
                ", date=" + date +
                '}';
    }
}

