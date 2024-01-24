package UI;

import Repositories.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RegisterController {
    @FXML
    private AnchorPane register_pane;
    @FXML
    private TextField user_name;
    @FXML
    private TextField password;
    @FXML
    private TextField secondPassword;
    @FXML
    private TextField email;
    @FXML
    private Label text_message;
    @FXML
    private Button reg_button;

    private String username;
    private String pass;
    private String secondpass;
    private String ema;


    @FXML
    private void changeToMain(ActionEvent event)
    {
        if(event.getSource().equals(reg_button))
        {
            setRegister();
            if(pass.equals(secondpass) && !username.isEmpty())
            {
                UserRepository.insertUser(username,pass,ema);

                try{
                    Stage stage = (Stage) register_pane.getScene().getWindow();
                    stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UI/example/gofleetgo/main.fxml"));
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
            else
            {
                text_message.setText("Hasła nie są idętyczne!");
                System.out.println("Hasła nie zgadzają się!");
            }
        }
    }
    @FXML
    private void setRegister()
    {
        username = user_name.getText();
        pass = password.getText();
        secondpass = secondPassword.getText();
        ema = email.getText();
    }
}
