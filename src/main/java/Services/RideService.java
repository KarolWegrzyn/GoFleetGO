package Services;

import Classes.*;
import Repositories.*;

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

        Vehicle vehicle = VehicleRepository.findVehicleById(vehicleId);
        Model model = ModelRepository.findModelById(vehicle.getModelID());

        double cost = route.getDistance() * model.getPrice();
        UserRepository.updateBalance(userId, -cost);
//        double[] finishPosition = new double[2];
//        finishPosition[0] = vehicle.getRow();
//        finishPosition[1] = vehicle.getColumn();
//
//        updateRoute(ride.getRouteID(),finishPosition[0], finishPosition[1], vehicle.getDistance());
//        System.out.println("vehicle finished at: " + finishPosition[0] + ", " + finishPosition[1] + " | with distance: " + vehicle.getDistance());
    }
}
