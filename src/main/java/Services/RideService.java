package Services;

import Classes.*;
import Repositories.RideRepository;
import Repositories.RouteRepository;
import Repositories.VehicleRepository;
import util.GlobalData;

import static Repositories.ReservationRepository.findReservationById;
import static Repositories.RouteRepository.createNewRoute;
import static Repositories.RouteRepository.updateRoute;
import static Repositories.UserRepository.findUserById;
import static Repositories.VehicleRepository.findVehicleById;

public class RideService {
    //make an implementation for updating ride, route, fuel consumption
    public static void finishRide(Route route, Integer userId) {
        Ride ride = RideRepository.findRideByUserIdWithActiveState(userId);
        System.out.println("test");
        RideRepository.updateRideState(ride.getRideID(), Ride.RideStatus.finished);

        int vehicleId = ride.getVehicleID();
        VehicleRepository.updateStatus(vehicleId, Vehicle.VehicleStatus.free);
        VehicleRepository.updateLocation(vehicleId, route.getFinishRow(), route.getFinishColumn());
        VehicleRepository.updateFuelLevel(vehicleId, route.getDistance());

        RouteRepository.updateRoute(ride.getRouteID(), route.getFinishRow(),route.getFinishColumn(), route.getDistance());


//        double[] finishPosition = new double[2];
//        finishPosition[0] = vehicle.getRow();
//        finishPosition[1] = vehicle.getColumn();
//
//        updateRoute(ride.getRouteID(),finishPosition[0], finishPosition[1], vehicle.getDistance());
//        System.out.println("vehicle finished at: " + finishPosition[0] + ", " + finishPosition[1] + " | with distance: " + vehicle.getDistance());
    }
}
