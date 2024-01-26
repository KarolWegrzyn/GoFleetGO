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

        RouteRepository.updateRoute(ride.getRouteID(), route.getFinishRow(),route.getFinishColumn(), route.getDistance());

        Vehicle vehicle = VehicleRepository.findVehicleById(vehicleId);
        Model model = ModelRepository.findModelById(vehicle.getModelID());

        double cost = route.getDistance() * model.getPrice();
        UserRepository.updateBalance(userId, -cost);
    }

    public static void finishRideByColision(Route route, Integer userId) {
        Ride ride = RideRepository.findRideByUserIdWithActiveState(userId);
        System.out.println("test");
        RideRepository.updateRideState(ride.getRideID(), Ride.RideStatus.finished);

        int vehicleId = ride.getVehicleID();
        VehicleRepository.updateStatus(vehicleId, Vehicle.VehicleStatus.disabled);
        VehicleRepository.updateLocation(vehicleId, route.getFinishRow(), route.getFinishColumn());

        RouteRepository.updateRoute(ride.getRouteID(), route.getFinishRow(),route.getFinishColumn(), route.getDistance());

        Vehicle vehicle = VehicleRepository.findVehicleById(vehicleId);
        Model model = ModelRepository.findModelById(vehicle.getModelID());

        double cost = route.getDistance() * model.getPrice() + 500;
        UserRepository.updateBalance(userId, -cost);
    }
}
