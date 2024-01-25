package UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private void changeToWypSam(ActionEvent event)
    {
        if(event.getSource().equals(button_wyp_sam))
        {
            try{
                 Stage stage = (Stage) borderPane_menu.getScene().getWindow();
                 stage.close();
//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/UI/example/gofleetgo/wypozycz_samochod.fxml"));
//                Parent root = (Parent) fxmlLoader.load();
//                Stage stage1 = new Stage();
//                stage1.setScene(new Scene(root));
//                stage1.setTitle("GoFleetGo");
//                stage1.show();

                MovingObjectWithObstacles d = new MovingObjectWithObstacles();
                Stage stage1 = new Stage();
                stage1.setScene(d.start());
                stage1.show();


//                popupStage.initModality(Modality.APPLICATION_MODAL);
//                popupStage.setTitle("Formularz");
//                popupStage.setScene(d.start());
//                popupStage.showAndWait();

            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
