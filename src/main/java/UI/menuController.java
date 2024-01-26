package UI;

import DTO.ClientRequest;
import DTO.ServerResponse;
import DTO.VehicleModelData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.GlobalData;
import util.NetworkClient;

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
    private Label balance;

    @FXML
    public void initialize(){
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setPrivateToken(GlobalData.getUserId());
        clientRequest.setAction("showUserBalance");

        ServerResponse serverResponse = NetworkClient.sendRequest(clientRequest);
        double data = (double) serverResponse.getData();
        balance.setText(String.valueOf(data));
    }

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
