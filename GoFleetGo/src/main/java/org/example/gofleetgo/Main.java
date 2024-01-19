package org.example.gofleetgo;

import Managers.ConnectionManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static Repositories.VehicleRepository.findVehicleById;
import static org.example.gofleetgo.DataBaseController.connect;
import static org.example.gofleetgo.DataBaseController.disconnect;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("GoFleetGo");
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) throws SQLException {
       //DataBaseController controller = new DataBaseController();
       //Connection connection = connect();
        Connection connection = ConnectionManager.getConnection();
        //System.out.println(controller.isDataLoginCorrect("root","1111"));
        System.out.println(connection);
        System.out.printf(findVehicleById(1).toString());
        launch();
    }
}