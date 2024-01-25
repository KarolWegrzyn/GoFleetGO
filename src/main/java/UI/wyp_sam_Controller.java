package UI;

import Classes.Route;
import DTO.ClientRequest;
import DTO.EndRideData;
import DTO.ServerResponse;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import util.GlobalData;
import util.NetworkClient;

public class wyp_sam_Controller {

    //@FXML
   // private AnchorPane[][] mapa = new AnchorPane[6][8];
    @FXML
    private AnchorPane r0k0;

    @FXML
    protected void onClick()
    {
        ClientRequest clientRequest = new ClientRequest();
        Route route = new Route();
        route.setDistance(2);
        route.setStartRow(1);
        route.setStartColumn(1);
        route.setFinishRow(2);
        route.setFinishColumn(2);

        EndRideData endRideData = new EndRideData();
        endRideData.setRideId(GlobalData.getCurrentRideId());
        endRideData.setRoute(route);

        clientRequest.setAction("endRide");
        clientRequest.setData(endRideData);

        NetworkClient.sendRequest(clientRequest);
        r0k0.setStyle("-fx-background-color: #008000");
    }
}
