package Classes;

import Repositories.RideRepository;

import static Repositories.ReservationRepository.findReservationById;
import static Repositories.RouteRepository.updateRoute;
import static Repositories.RouteRepository.createNewRoute;
import static Repositories.UserRepository.findUserById;
import static Repositories.VehicleRepository.findVehicleById;

public class Ride {
    private int rideID;
    private int userID;
    private int vehicleID;
    private Integer reservationID;
    private int routeID;

    public Ride(int rideID, int userID, int vehicleID, Integer reservationID, int routeID) {
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

        int[] startingPosition = vehicle.Drive();
        int routeId = createNewRoute(startingPosition);
        Ride createdRide = RideRepository.createNewRide(userID, vehicleID, reservationID, routeId);
        return  createdRide;
    }

    public static Ride createNewRide(int userID, Vehicle vehicle) throws Exception {
        User user = findUserById(userID);
        assert user != null;

        int[] startingPosition = new int[2];
        startingPosition[0] = vehicle.getRow();
        startingPosition[1] = vehicle.getColumn();

        int routeId = createNewRoute(startingPosition);
        Ride createdRide = RideRepository.createNewRide(userID, vehicle.getVehicleID(), null, routeId);
        vehicle.Drive();
        return createdRide;
    }

    public static void stopRide(Ride ride, Vehicle vehicle) {

        int[] finishPosition = new int[2];
        finishPosition[0] = vehicle.getRow();
        finishPosition[1] = vehicle.getColumn();

        vehicle.StopDrive();
        updateRoute(ride.routeID,finishPosition[0], finishPosition[1], vehicle.getDistance());
        System.out.println("vehicle finished at: " + finishPosition[0] + ", " + finishPosition[1] + " | with distance: " + vehicle.getDistance());
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

