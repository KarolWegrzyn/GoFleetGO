module UI{
    requires javafx.controls;
    requires javafx.fxml;


    requires java.sql;

    opens UI to javafx.fxml;
    exports UI;
}