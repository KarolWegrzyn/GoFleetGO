package Services;

import Classes.Reservation;
import Classes.Ride;
import Classes.User;
import Classes.Vehicle;
import Repositories.RideRepository;

import static Repositories.ReservationRepository.findReservationById;
import static Repositories.RouteRepository.createNewRoute;
import static Repositories.RouteRepository.updateRoute;
import static Repositories.UserRepository.findUserById;
import static Repositories.VehicleRepository.findVehicleById;

public class RideService {
    //make an implementation for updating ride, route, fuel consumption
    public static Ride createNewRide(int userID, Vehicle vehicle) throws Exception {
        User user = findUserById(userID);
        assert user != null;

        double[] startingPosition = new double[2];
        startingPosition[0] = vehicle.getRow();
        startingPosition[1] = vehicle.getColumn();

        int routeId = createNewRoute(startingPosition);
        Ride createdRide = RideRepository.createNewRide(userID, vehicle.getVehicleID(), null, routeId);
        return createdRide;
    }

    public static Ride createNewRide(int userID, int vehicleID, Integer reservationID) throws Exception {
        User user = findUserById(userID);
        assert user != null;
        Vehicle vehicle = findVehicleById(vehicleID);
        assert vehicle != null;
        Reservation reservation = findReservationById(reservationID);
        assert reservation != null;

        if (reservation.getUserID() != userID){
            throw new Exception("createNewRideException: userId and userId from reservation are not the same");
        }

        double[] startingPosition = new double[2];
        startingPosition[0] = vehicle.getRow();
        startingPosition[1] = vehicle.getColumn();

        int routeId = createNewRoute(startingPosition);
        Ride createdRide = RideRepository.createNewRide(userID, vehicleID, reservationID, routeId);
        return  createdRide;
    }

    public static void stopRide(Ride ride, Vehicle vehicle) {

        double[] finishPosition = new double[2];
        finishPosition[0] = vehicle.getRow();
        finishPosition[1] = vehicle.getColumn();

        updateRoute(ride.getRouteID(),finishPosition[0], finishPosition[1], vehicle.getDistance());
        System.out.println("vehicle finished at: " + finishPosition[0] + ", " + finishPosition[1] + " | with distance: " + vehicle.getDistance());
    }
}
