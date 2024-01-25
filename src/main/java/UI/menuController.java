package UI;

import Classes.Ride;
import Classes.Vehicle;
import DTO.ClientRequest;
import DTO.ServerResponse;
import Repositories.VehicleRepository;
import Services.RideService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.NetworkClient;
import util.GlobalData;

import java.util.ArrayList;

public class menuController {
    @FXML
    private BorderPane borderPane_menu;
    @FXML
    private Button butoon_zar_przejazd;
    @FXML
    private Button button_wyp_sam;
    @FXML
    private Button button_hist_przejazd;

    @FXML
    private void changeToWypSam(ActionEvent event) {
        if (event.getSource().equals(button_wyp_sam)) {
            try {
                Vehicle vehicle = VehicleRepository.findVehicleById(1);
                //Do not forget about not null reservation
                Ride ride = RideService.createNewRide(
                        GlobalData.getUserId(),
                        vehicle
                );
//
//                ride.setRideID(1);
//                ride.setUserID(GlobalData.getUserId());
//                ride.setVehicleID(1);
//                ride.setReservationID(1);
//                ride.setReservationID(null);
//                ride.setRouteID();
                ClientRequest clientRequest = new ClientRequest();
                clientRequest.setData(ride);
                clientRequest.setAction("createNewRide");

                ServerResponse serverResponse = NetworkClient.sendRequest(clientRequest);
                Integer rideId = (Integer) serverResponse.getData();
                GlobalData.setCurrentRideId(rideId);

                Stage stage = (Stage) borderPane_menu.getScene().getWindow();
                stage.close();
                MovingObjectWithObstacles d = new MovingObjectWithObstacles();
                Stage stage1 = new Stage();
                stage1.setScene(d.start());
                stage1.show();



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
