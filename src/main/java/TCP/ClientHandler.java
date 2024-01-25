package TCP;


import Classes.Ride;
import DTO.ClientRequest;
import DTO.EndRideData;
import DTO.LoginData;
import DTO.ServerResponse;
import Repositories.RideRepository;
import UI.GetUserExample;

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
            if (clientRequest.getAction().equals("login")) {
                LoginData data = (LoginData) clientRequest.getData();
                GetUserExample userExample = new GetUserExample();
                Integer userId = userExample.fetchUser(data.getUsername(), data.getPassword());
                serverResponse.setData(userId);
            } else if(clientRequest.getAction().equals("createNewRide")){
                Ride data = (Ride) clientRequest.getData();
                Ride newRide = RideRepository.createNewRide(data.getUserID(), data.getVehicleID(), data.getReservationID());
                serverResponse.setData(newRide.getRideID());
            } else if(clientRequest.getAction().equals("endRide")){
                EndRideData endRideData = (EndRideData) clientRequest.getData();
                RideRepository.finishRide(endRideData.getRideId(), endRideData.getRoute());
            }
            objectOutputStream.writeObject(serverResponse);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
