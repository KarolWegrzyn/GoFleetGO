package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class menuController {
    @FXML
    private BorderPane borderPane_menu;
    @FXML
    private Button butoon_zar_sam;
    @FXML
    private Button button_wyp_sam;
    @FXML
    private Button button_hist_przejazd;


    @FXML
    private void changeToZarSam(ActionEvent event){
        if(event.getSource().equals(butoon_zar_sam))
        {
            try{
                Stage stage = (Stage) borderPane_menu.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UI/example/gofleetgo/zar_sam.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage0 = new Stage();
                stage0.setScene(new Scene(root));
                stage0.setTitle("GoFleetGo");
                stage0.show();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    @FXML
    private void changeToWypSam(ActionEvent event) {
        if (event.getSource().equals(button_wyp_sam)) {
            try {
//                Vehicle vehicle = VehicleRepository.findVehicleById(1);
//                //Do not forget about not null reservation
//                Ride ride = RideService.createNewRide(
//                        GlobalData.getUserId(),
//                        vehicle
//                );
//
////
//                Ride ride = new Ride();
//                ride.setRideID(1);
//                ride.setUserID(GlobalData.getUserId());
//                ride.setVehicleID(2);
//                ride.setReservationID(null);
//                ClientRequest clientRequest = new ClientRequest();
//                clientRequest.setData(ride);
//                clientRequest.setAction("createNewRide");
//
//                ServerResponse serverResponse = NetworkClient.sendRequest(clientRequest);
//                Integer rideId = (Integer) serverResponse.getData();
//                GlobalData.setCurrentRideId(rideId);

                Stage stage = (Stage) borderPane_menu.getScene().getWindow();
                stage.close();
                MovingObjectWithObstacles d = new MovingObjectWithObstacles();
                d.setId(2); // ustalanie koloru
                Stage stage1 = new Stage();
                stage1.setScene(d.start());
                stage1.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
