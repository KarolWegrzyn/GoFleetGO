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

import java.util.regex.Pattern;

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
    private String emailValue;


    @FXML
    private void changeToMain(ActionEvent event)
    {
        boolean areDataValid = true;
        if(event.getSource().equals(reg_button))
        {
            //check email
            if (emailValue == null || emailValue.isBlank()) {
                text_message.setText("Email jest pusty!");
                System.out.println("Email jest pusty!");
                areDataValid = false;
            } else {
                String regex = ".*@.*\\..+";
                Pattern pattern = Pattern.compile(regex);
                if (!pattern.matcher(emailValue).matches()){
                    text_message.setText("Email jest w zlym formacie!");
                    System.out.println("Email Email jest w zlym formacie!");
                    areDataValid = false;
                }
            }

            //check password
            if(pass == null || pass.isBlank()) {
                text_message.setText("Hasło jest puste!");
                System.out.println("Hasło jest puste!");
                areDataValid = false;
            } else {
                if (!containsUpperCaseLetter(pass)) {
                    text_message.setText("Hasło nie zawiera dużej litery!");
                    System.out.println("Hasło nie zawiera dużej litery!");
                    areDataValid = false;
                }

                if (!containsDigit(pass)) {
                    text_message.setText("Hasło nie zawiera cyfry!");
                    System.out.println("Hasło nie zawiera cyfry!");
                    areDataValid = false;
                }

                if(!pass.equals(secondpass)) {
                    text_message.setText("Hasła nie są idętyczne!");
                    System.out.println("Hasła nie są idętyczne!");
                    areDataValid = false;
                }
            }

            setRegister();
            //check user name
            if (username == null || username.isBlank()) {
                text_message.setText("Nazwa uzytkownika jest pusta!");
                System.out.println("Nazwa uzytkownika jest pusta!");
                areDataValid = false;
            }

            if (areDataValid) {
                UserRepository.insertUser(username, pass, emailValue);

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
        }
    }
    @FXML
    private void setRegister()
    {
        username = user_name.getText();
        pass = password.getText();
        secondpass = secondPassword.getText();
        emailValue = email.getText();
    }

    private boolean containsUpperCaseLetter(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean containsDigit(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
}
