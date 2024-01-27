package UI;

import DTO.ClientRequest;
import DTO.LoginData;
import DTO.ServerResponse;
import Repositories.UserRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import util.GlobalData;
import util.NetworkClient;

public class AddBalanceController {
    @FXML
    private BorderPane add_balance_pane;
    @FXML
    private TextField balance_value;
    @FXML
    private Label error_message;
    @FXML
    private Button add_balance_button;
    @FXML
    private Button back_button;

    @FXML
    private void addBalanceToUser(ActionEvent event)
    {
        double money = 0;

        if(event.getSource().equals(add_balance_button))
        {
            boolean isDataValid = false;
            String stringOfBalanceValue = balance_value.getText();
            if (isDigit(stringOfBalanceValue) && !stringOfBalanceValue.isBlank()) {
                 money = Double.parseDouble(balance_value.getText());

                    if (money > 0) {
                        isDataValid = true;
                    } else {
                        error_message.setText("Liczba musi być większa niż 0");
                        System.out.println("Liczba musi być większa niż 0");
                    }
            } else {
                error_message.setText("Niepoprawna wartość");
                System.out.println("Niepoprawna wartość");
            }

            if (isDataValid){
                try{
                    UserRepository.updateBalance(GlobalData.getUserId(), money);

                    Stage stage = (Stage) add_balance_pane.getScene().getWindow();
                    stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UI/example/gofleetgo/menu.fxml"));
                    Parent root = fxmlLoader.load();
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
    private void changeToMenu(ActionEvent event) {
        if (event.getSource().equals(back_button)) {
            try {
                    Stage stage = (Stage) add_balance_pane.getScene().getWindow();
                    stage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UI/example/gofleetgo/menu.fxml"));
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

    private boolean isDigit(String s) {
        for (char c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
