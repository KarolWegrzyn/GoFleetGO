module org.example.gofleetgo {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens org.example.gofleetgo to javafx.fxml;
    exports org.example.gofleetgo;
}