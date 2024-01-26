package TCP;

import Classes.Ride;
import Classes.Route;
import Classes.User;
import Classes.Vehicle;
import DTO.*;
import Repositories.RideRepository;
import Repositories.RouteRepository;
import Repositories.VehicleRepository;
import Services.RideService;
import Services.VehicleService;

import java.io.*;
import java.net.Socket;

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

                case "createNewRide": {
                    Integer vehicleId = (Integer) clientRequest.getData();
                    Integer userId = clientRequest.getPrivateToken();

                    VehicleRepository.updateStatus(vehicleId, Vehicle.VehicleStatus.inUse);
                    Vehicle vehicle = VehicleRepository.findVehicleById(vehicleId);
                    Integer newRouteId = RouteRepository.createNewRoute(vehicle.getRow(), vehicle.getColumn());

                    Ride newRide = RideRepository.createNewRide(userId, vehicleId, null, newRouteId);

                    assert newRide != null;
                    serverResponse.setData(newRide.getRideID());
                    break;
                }

                case "endRide": {
                    Route route = (Route) clientRequest.getData();
                    RideService.finishRide(route, clientRequest.getPrivateToken());
                    break;
                }

                case "showVehicleData": {
                    Integer vehicleId = (Integer) clientRequest.getData();
                    VehicleModelData vehicleModelData = VehicleService.getVehicleModelDataById(vehicleId);

                    serverResponse.setData(vehicleModelData);
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
