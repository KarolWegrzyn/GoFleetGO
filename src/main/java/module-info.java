module UI{
    exports TCP;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens UI to javafx.fxml;
    exports UI;
}