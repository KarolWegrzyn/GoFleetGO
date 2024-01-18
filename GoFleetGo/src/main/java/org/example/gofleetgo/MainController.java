package org.example.gofleetgo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainController {
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button button_login;
    @FXML
    private TextField text_us_log;
    @FXML
    private TextField text_pas;

    @FXML
    private void changeToMenu(ActionEvent event)
    {
        if(event.getSource().equals(button_login))
        {
            try{
                Stage stage = (Stage) borderPane.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Stage stage1 = new Stage();
                stage1.setScene(new Scene(root));
                stage1.setTitle("GoFleetGo");
                stage1.show();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}