package Classes;

import Repositories.RideRepository;

import java.io.Serializable;

import static Repositories.ReservationRepository.findReservationById;
import static Repositories.RouteRepository.updateRoute;
import static Repositories.RouteRepository.createNewRoute;
import static Repositories.UserRepository.findUserById;
import static Repositories.VehicleRepository.findVehicleById;

public class Ride implements Serializable {
    private int rideID;
    private int userID;
    private int vehicleID;
    private Integer reservationID;
    private int routeID;

    public enum RideStatus{
        active,
        finished
    }

    public Ride(int rideID, int userID, int vehicleID, Integer reservationID, Integer routeID) {
        this.rideID = rideID;
        this.userID = userID;
        this.vehicleID = vehicleID;
        this.reservationID = reservationID;
        this.routeID = routeID;
    }

    public Ride() {
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

    public Integer getReservationID() {
        return reservationID;
    }

    public void setReservationID(Integer reservationID) {
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

