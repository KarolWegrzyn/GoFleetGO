package Classes;

import Repositories.RideRepository;

import static Repositories.ReservationRepository.findReservationById;
import static Repositories.RouteRepository.createNewRoute;
import static Repositories.UserRepository.findUserById;
import static Repositories.VehicleRepository.findVehicleById;

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

    public static Ride createNewRide(int userID, int vehicleID, int reservationID) throws Exception {
        User user = findUserById(userID);
        assert user != null;
        Vehicle vehicle = findVehicleById(vehicleID);
        assert vehicle != null;
        Reservation reservation = findReservationById(reservationID);
        assert reservation != null;

        if (reservation.getUserID() != userID){
            throw new Exception("createNewRideException: userId and userId from reservation are not the same");
        }
        int[] startingPosition = vehicle.drive();
        int routeId = createNewRoute(startingPosition);
        return RideRepository.createNewRide(userID, vehicleID, reservationID, routeId);
    }

    public static Ride createNewRide(int userID, int vehicleID) throws Exception {
        User user = findUserById(userID);
        assert user != null;
        Vehicle vehicle = findVehicleById(vehicleID);
        assert vehicle != null;

        int[] startingPosition = vehicle.drive();
        int routeId = createNewRoute(startingPosition);
        return RideRepository.createNewRide(userID, vehicleID, null, routeId);
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

