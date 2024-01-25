package TCP;

import Classes.Ride;
import Classes.User;
import DTO.ClientRequest;
import DTO.EndRideData;
import DTO.LoginData;
import DTO.ServerResponse;
import Repositories.RideRepository;

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
                    Ride rideData = (Ride) clientRequest.getData();
                    Ride newRide;
                    if (rideData.getReservationID() == null){
                        newRide = RideRepository.createNewRide(rideData.getUserID(), rideData.getVehicleID(), null, rideData.getRouteID());
                    } else {
                        newRide = RideRepository.createNewRide(rideData.getUserID(), rideData.getVehicleID(), rideData.getReservationID(), rideData.getRouteID());

                    }
                    assert newRide != null;
                    serverResponse.setData(newRide.getRideID());
                    break;
                }

                case "endRide": {
                    EndRideData endRideData = (EndRideData) clientRequest.getData();
                    RideRepository.finishRide(endRideData.getRideId(), endRideData.getRoute());
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
