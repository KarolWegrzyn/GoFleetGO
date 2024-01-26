package UI;

import DTO.ClientRequest;
import DTO.ServerResponse;
import DTO.VehicleModelData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.GlobalData;
import util.NetworkClient;

public class ZarSamController {
    private int id; //1-zielony, 2-niebieski, 3-czerwony, 4-zolty

    @FXML
    private void initialize(){
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setData(1);
        clientRequest.setAction("showVehicleData");

        ServerResponse serverResponse = NetworkClient.sendRequest(clientRequest);
        VehicleModelData data = (VehicleModelData) serverResponse.getData();
        g_model.setText(String.valueOf(data.getBrand()));
        g_zasieg.setText(String.valueOf(data.getRange()));
        g_pozycja.setText("X: " + String.valueOf(data.getRow()) + " Y: " + String.valueOf(data.getColumn()));
        g_stan_paliwa.setText(String.valueOf(data.getFuelLevel()));
        g_rok_prod.setText(String.valueOf(data.getYearOfProduction().getYear()));
        g_zasieg.setText(String.valueOf(data.getRange()));
        g_status.setText(String.valueOf(data.getStatus()));
        g_koszt.setText(String.valueOf(data.getPrice() + " zl"));

        clientRequest.setData(2);
        clientRequest.setAction("showVehicleData");

        serverResponse = NetworkClient.sendRequest(clientRequest);
        data = (VehicleModelData) serverResponse.getData();
        b_model.setText(String.valueOf(data.getBrand()));
        b_zasieg.setText(String.valueOf(data.getRange()));
        b_pozycja.setText("X: " + String.valueOf(data.getRow()) + " Y: " + String.valueOf(data.getColumn()));
        b_stan_paliwa.setText(String.valueOf(data.getFuelLevel()));
        b_rok_prod.setText(String.valueOf(data.getYearOfProduction().getYear()));
        b_zasieg.setText(String.valueOf(data.getRange()));
        b_status.setText(String.valueOf(data.getStatus()));
        b_koszt.setText(String.valueOf(data.getPrice() + " zl"));

        clientRequest.setData(3);
        clientRequest.setAction("showVehicleData");

        serverResponse = NetworkClient.sendRequest(clientRequest);
        data = (VehicleModelData) serverResponse.getData();
        r_model.setText(String.valueOf(data.getBrand()));
        r_zasieg.setText(String.valueOf(data.getRange()));
        r_pozycja.setText("X: " + String.valueOf(data.getRow()) + " Y: " + String.valueOf(data.getColumn()));
        r_stan_paliwa.setText(String.valueOf(data.getFuelLevel()));
        r_rok_prod.setText(String.valueOf(data.getYearOfProduction().getYear()));
        r_zasieg.setText(String.valueOf(data.getRange()));
        r_status.setText(String.valueOf(data.getStatus()));
        r_koszt.setText(String.valueOf(data.getPrice() + " zl"));

        clientRequest.setData(4);
        clientRequest.setAction("showVehicleData");

        serverResponse = NetworkClient.sendRequest(clientRequest);
        data = (VehicleModelData) serverResponse.getData();
        y_model.setText(String.valueOf(data.getBrand()));
        y_zasieg.setText(String.valueOf(data.getRange()));
        y_pozycja.setText("X: " + String.valueOf(data.getRow()) + " Y: " + String.valueOf(data.getColumn()));
        y_stan_paliwa.setText(String.valueOf(data.getFuelLevel()));
        y_rok_prod.setText(String.valueOf(data.getYearOfProduction().getYear()));
        y_zasieg.setText(String.valueOf(data.getRange()));
        y_status.setText(String.valueOf(data.getStatus()));
        y_koszt.setText(String.valueOf(data.getPrice() + " zl"));
    }

    @FXML
    private AnchorPane mainAnPen;
    @FXML
    private Button start_button;

    @FXML
    private AnchorPane greenAnPen;
    @FXML
    private Label g_model;
    @FXML
    private Label g_pozycja;
    @FXML
    private Label g_stan_paliwa;
    @FXML
    private Label g_rok_prod;
    @FXML
    private Label g_zasieg;
    @FXML
    private Label g_status;
    @FXML
    private Label g_koszt;
    @FXML
    private CheckBox greenCheck;


    @FXML
    private AnchorPane blueAnPen;
    @FXML
    private Label b_model;
    @FXML
    private Label b_pozycja;
    @FXML
    private Label b_stan_paliwa;
    @FXML
    private Label b_rok_prod;
    @FXML
    private Label b_zasieg;
    @FXML
    private Label b_status;
    @FXML
    private Label b_koszt;
    @FXML
    private CheckBox blueCheck;


    @FXML
    private AnchorPane redAnPen;
    @FXML
    private Label r_model;
    @FXML
    private Label r_pozycja;
    @FXML
    private Label r_stan_paliwa;
    @FXML
    private Label r_rok_prod;
    @FXML
    private Label r_zasieg;
    @FXML
    private Label r_status;
    @FXML
    private Label r_koszt;
    @FXML
    private CheckBox redCheck;


    @FXML
    private AnchorPane yellowAnPen;
    @FXML
    private Label y_model;
    @FXML
    private Label y_pozycja;
    @FXML
    private Label y_stan_paliwa;
    @FXML
    private Label y_rok_prod;
    @FXML
    private Label y_zasieg;
    @FXML
    private Label y_status;
    @FXML
    private Label y_koszt;
    @FXML
    private CheckBox yellowCheck;

    @FXML
    private void sendIdOfCar(ActionEvent event) throws Exception {
        if(greenCheck.isSelected())
        {
            id=1;
        }else if(blueCheck.isSelected())
        {
            id=2;
        }else if(redCheck.isSelected())
        {
            id=3;
        }else if(yellowCheck.isSelected())
        {
            id=4;
        }

        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setData(id);
        clientRequest.setPrivateToken(GlobalData.getUserId());

        clientRequest.setAction("createNewRide");
        ServerResponse serverResponse = NetworkClient.sendRequest(clientRequest);
        System.out.println("id of chosen vehicle: " + id);

        if (serverResponse.getResultCode() == 500){
            System.out.println("vehicle with id: " + id + " is not free");
        } else {
            Stage stage = (Stage) mainAnPen.getScene().getWindow();
            stage.close();
            MovingObjectWithObstacles d = new MovingObjectWithObstacles();
            d.setId(id); // ustalanie koloru
            Stage stage1 = new Stage();
            stage1.setScene(d.start());
            stage1.show();
        }
    }
}
