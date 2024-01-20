package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/UI/example/gofleetgo/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("GoFleetGo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        launch();
    }
}
        /* Logowanie w konsoli
        System.out.print("Podaj nazwe uzytkownika: ");
        Scanner scanner = new Scanner(System.in);
        String user = scanner.nextLine();
        System.out.print("Podaj nazwe haslo: ");
        String password = scanner.nextLine();
        GetUserExample.connection(user, password);
        */