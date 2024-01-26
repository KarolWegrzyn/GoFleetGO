package TCP;

import Classes.Ride;
import Classes.Route;
import Classes.User;
import Classes.Vehicle;
import DTO.*;
import Repositories.RideRepository;
import Repositories.RouteRepository;
import Repositories.UserRepository;
import Repositories.VehicleRepository;
import Services.RideService;
import Services.VehicleService;

import java.io.*;
import java.net.Socket;

import static Repositories.UserRepository.getUserBalanceById;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream())) {
            ClientRequest clientRequest = (ClientRequest) objectInputStream.readObject();
            ServerResponse serverResponse = new ServerResponse();

            switch (clientRequest.getAction()) {
                case "login": {
                    LoginData data = (LoginData) clientRequest.getData();
                    User userExample = new User();
                    Integer userId = userExample.fetchUser(data.getUsername(), data.getPassword());
                    serverResponse.setData(userId);
                    break;
                }

                case "startRide": {
                    Integer userId = clientRequest.getPrivateToken();
                    Ride ride = RideRepository.findRideByUserIdWithActiveState(userId);

                    Integer vehicleId = ride.getVehicleID();
                    StartRideData startRideData = VehicleService.getVehicleStartDataById(vehicleId);

                    serverResponse.setData(startRideData);
                    break;
                }

                case "createNewRide": {
                    Integer vehicleId = (Integer) clientRequest.getData();
                    Integer userId = clientRequest.getPrivateToken();

                    Vehicle vehicle = VehicleRepository.findVehicleById(vehicleId);
                    if (!vehicle.getStatus().equals(Vehicle.VehicleStatus.free)){
                        serverResponse.setResultCode(500);
                    } else {
                        VehicleRepository.updateStatus(vehicleId, Vehicle.VehicleStatus.inUse);
                        Integer newRouteId = RouteRepository.createNewRoute(vehicle.getRow(), vehicle.getColumn());
                        Ride newRide = RideRepository.createNewRide(userId, vehicleId, null, newRouteId);
                        assert newRide != null;
                        serverResponse.setData(newRide.getRideID());
                    }
                    break;
                }

                case "endRide": {
                    Route route = (Route) clientRequest.getData();
                    RideService.finishRide(route, clientRequest.getPrivateToken());
                    break;
                }

                case "endRideByColision": {
                    Route route = (Route) clientRequest.getData();
                    RideService.finishRideByColision(route, clientRequest.getPrivateToken());
                    break;
                }

                case "updateLocation": {
                    UpdateVehicleData updateVehicleData = (UpdateVehicleData) clientRequest.getData();
                    Integer userId = clientRequest.getPrivateToken();

                    VehicleRepository.updateLocation(updateVehicleData.getVehicleId(), updateVehicleData.getRow(), updateVehicleData.getColumn());
                    break;
                }

                case "updateFuelLevel": {
                    UpdateVehicleData updateVehicleData = (UpdateVehicleData) clientRequest.getData();
                    Integer userId = clientRequest.getPrivateToken();

                    VehicleRepository.updateFuelLevel(updateVehicleData.getVehicleId(), updateVehicleData.getDistanceFromLastUpdate());
                    break;
                }

                case "showVehicleData": {
                    Integer vehicleId = (Integer) clientRequest.getData();
                    VehicleModelData vehicleModelData = VehicleService.getVehicleModelDataById(vehicleId);

                    serverResponse.setData(vehicleModelData);
                    break;
                }

                case "showUserBalance": {
                    Integer userId = clientRequest.getPrivateToken();
                    double balance = UserRepository.getUserBalanceById(userId);

                    serverResponse.setData(balance);
                    break;
                }

                default:
                    System.out.println("unknown action");
                    break;
            }


            objectOutputStream.writeObject(serverResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
