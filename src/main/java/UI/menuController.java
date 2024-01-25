package UI;

import Classes.Ride;
import DTO.ClientRequest;
import DTO.ServerResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.NetworkClient;
import util.GlobalData;

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
                Ride ride = new Ride();
                ride.setUserID(GlobalData.getUserId());
                ride.setVehicleID(1);
                ride.setReservationID(1);

                ClientRequest clientRequest = new ClientRequest();
                clientRequest.setData(ride);
                clientRequest.setAction("createNewRide");

                ServerResponse serverResponse = NetworkClient.sendRequest(clientRequest);
                Integer rideId = (Integer) serverResponse.getData();
                GlobalData.setCurrentRideId(rideId);

                Stage stage = (Stage) borderPane_menu.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UI/example/gofleetgo/wypozycz_samochod.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage1 = new Stage();
                stage1.setScene(new Scene(root));
                stage1.setTitle("GoFleetGo");
                stage1.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
