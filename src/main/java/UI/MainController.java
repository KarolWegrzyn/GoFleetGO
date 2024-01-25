package UI;

import DTO.ClientRequest;
import DTO.LoginData;
import DTO.ServerResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.NetworkClient;
import util.GlobalData;

public class MainController {
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button button_login;
    @FXML
    private Button button_register;
    @FXML
    private TextField text_us_log;
    @FXML
    private TextField text_pas;

    @FXML
    private Label text_message;


    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @FXML
    private void changeToMenu(ActionEvent event) {
        if (event.getSource().equals(button_login)) {
            try {
                username = text_us_log.getText();
                password = text_pas.getText();

                ClientRequest clientRequest = new ClientRequest();
                LoginData loginData = new LoginData(username, password);

                clientRequest.setAction("login");
                clientRequest.setData(loginData);

                ServerResponse serverResponse = NetworkClient.sendRequest(clientRequest);
                Integer userId = (Integer) serverResponse.getData();

                if (userId != -1) {
                    GlobalData.setUserId(userId);
                    Stage stage = (Stage) borderPane.getScene().getWindow();
                    stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UI/example/gofleetgo/menu.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    Stage stage1 = new Stage();
                    stage1.setScene(new Scene(root));
                    stage1.setTitle("GoFleetGo");
                    stage1.show();
                } else {
                    text_message.setText("Błędne dane logowania, Spróbuj ponownie");
                    System.out.println("nie zalogowano");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void changeToRegister(ActionEvent event) {
        if (event.getSource().equals(button_register)) {
            try {
                Stage stage = (Stage) borderPane.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UI/example/gofleetgo/register.fxml"));
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

    /*
    @FXML
    private void saveCredentials(ActionEvent event) {
        username = text_us_log.getText();
        password = text_pas.getText();

        // Tutaj możesz wykorzystać username i password, np. zapisując je do pliku
        // lub przekazując do innych metod w celu dalszego przetwarzania.
        System.out.println("Zapisano dane: " + username + ", " + password);
    }
    */
//System.out.println("Zapisano dane: " + username + ", " + password);