package TCP;

import DTO.ClientRequest;
import DTO.LoginData;
import DTO.ServerResponse;
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
            if (clientRequest.getAction().equals("login")) {
                LoginData data = (LoginData) clientRequest.getData();
                System.out.println(data.getUsername());
                System.out.println(data.getPassword());

                GetUserExample userExample = new GetUserExample();
                Integer userId = userExample.fetchUser(data.getUsername(), data.getPassword());

                ServerResponse serverResponse = new ServerResponse();
                serverResponse.setData(userId);

                objectOutputStream.writeObject(serverResponse);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
