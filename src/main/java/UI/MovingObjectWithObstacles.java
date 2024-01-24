package UI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MovingObjectWithObstacles extends Application {

    private static final double OBJECT_RADIUS = 20.0;
    private static final double OBJECT_SPEED_BLUE = 5.0;
    private static final double OBJECT_SPEED_GREEN = 2.5;
    private static final double OBJECT_SPEED_RED = 10.0;
    private static final double OBJECT_SPEED_YELLOW = 20.0;

    private Circle object;
    private List<Rectangle> obstacles;
    private Label collisionLabel;
    private Label xLabel;
    private Label yLabel;
    private Label distanceLabel;
    private Label colorLabel;

    private double totalDistance = 0.0;

    @Override
    public void start(Stage primaryStage) {
        Pane mapPane = new Pane();

        // Inicjalizacja etykiety na komunikat o kolizji
        collisionLabel = new Label();
        collisionLabel.setLayoutX(10);
        collisionLabel.setLayoutY(10);
        mapPane.getChildren().add(collisionLabel);

        // Inicjalizacja etykiety na położenie w osi X
        xLabel = new Label("X: ");
        xLabel.setLayoutX(10);
        xLabel.setLayoutY(460);
        mapPane.getChildren().add(xLabel);

        // Inicjalizacja etykiety na położenie w osi Y
        yLabel = new Label("Y: ");
        yLabel.setLayoutX(100);
        yLabel.setLayoutY(460);
        mapPane.getChildren().add(yLabel);

        // Inicjalizacja etykiety na przebytą drogę
        distanceLabel = new Label("Distance: 0.0");
        distanceLabel.setLayoutX(300);
        distanceLabel.setLayoutY(460);
        mapPane.getChildren().add(distanceLabel);

        // Inicjalizacja etykiety na nazwę koloru
        colorLabel = new Label("Color: Green");
        colorLabel.setLayoutX(500);
        colorLabel.setLayoutY(460);
        mapPane.getChildren().add(colorLabel);

        // Inicjalizacja obiektu (kółka)
        object = new Circle(OBJECT_RADIUS, Color.GREEN); // Startujemy od koloru zielonego
        placeObjectOnMap(50, 50); // Początkowa pozycja obiektu
        mapPane.getChildren().add(object);

        // Inicjalizacja przeszkód (kwadratów)
        obstacles = new ArrayList<>();
        Rectangle obstacle1 = createObstacle(100, 100, 50, 50);
        Rectangle obstacle2 = createObstacle(250, 100, 50, 50);
        Rectangle obstacle3 = createObstacle(400, 100, 50, 50);
        Rectangle obstacle4 = createObstacle(550, 100, 50, 50);
        Rectangle obstacle5 = createObstacle(100, 300, 50, 50);
        Rectangle obstacle6 = createObstacle(250, 300, 50, 50);
        Rectangle obstacle7 = createObstacle(400, 300, 50, 50);
        Rectangle obstacle8 = createObstacle(550, 300, 50, 50);

        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        obstacles.add(obstacle4);
        obstacles.add(obstacle5);
        obstacles.add(obstacle6);
        obstacles.add(obstacle7);
        obstacles.add(obstacle8);

        mapPane.getChildren().addAll(obstacle1, obstacle2, obstacle3, obstacle4,
                obstacle5, obstacle6, obstacle7, obstacle8);

        // Obsługa zdarzeń klawiatury
        mapPane.setOnKeyPressed(event -> handleKeyPress(event.getCode()));

        Scene scene = new Scene(mapPane, 700, 500); // Zmiana rozmiarów sceny
        primaryStage.setTitle("Moving Object with Obstacles");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Ustawienie fokusu na mapie, aby obsługa zdarzeń klawiatury działała
        mapPane.requestFocus();
    }

    private Rectangle createObstacle(double x, double y, double width, double height) {
        Rectangle obstacle = new Rectangle(x, y, width, height);
        obstacle.setFill(Color.RED);
        return obstacle;
    }

    private void placeObjectOnMap(double x, double y) {
        object.setCenterX(x);
        object.setCenterY(y);
        object.setFill(Color.GREEN); // Resetowanie koloru na zielony
        updatePositionLabels();
    }

    private void updatePositionLabels() {
        xLabel.setText("X: " + object.getCenterX());
        yLabel.setText("Y: " + object.getCenterY());
    }

    private void updateDistanceLabel(double distance) {
        totalDistance += distance;
        distanceLabel.setText("Distance: " + totalDistance);
    }

    private void updateColorLabel(Color color) {
        String colorName;
        if (color == Color.BLUE) {
            colorName = "Blue";
        } else if (color == Color.RED) {
            colorName = "Red";
        } else if (color == Color.YELLOW) {
            colorName = "Yellow";
        } else {
            colorName = "Green";
        }
        colorLabel.setText("Color: " + colorName);
    }

    private void handleKeyPress(KeyCode code) {
        double newX = object.getCenterX();
        double newY = object.getCenterY();

        double speed;
        if (object.getFill() == Color.BLUE) {
            speed = OBJECT_SPEED_BLUE;
        } else if (object.getFill() == Color.RED) {
            speed = OBJECT_SPEED_RED;
        } else if (object.getFill() == Color.YELLOW) {
            speed = OBJECT_SPEED_YELLOW;
        } else {
            speed = OBJECT_SPEED_GREEN; // Dla zielonego
        }

        switch (code) {
            case UP:
                newY -= speed;
                break;
            case DOWN:
                newY += speed;
                break;
            case LEFT:
                newX -= speed;
                break;
            case RIGHT:
                newX += speed;
                break;
            case R:
                // Zmiana koloru po naciśnięciu klawisza "R"
                changeColorAndSpeed();
                break;
            default:
                // Inne klawisze można obsłużyć według potrzeb
                break;
        }

        // Sprawdzenie kolizji z przeszkodami
        if (!checkCollision(newX, newY)) {
            double distance = calculateDistance(object.getCenterX(), object.getCenterY(), newX, newY);
            object.setCenterX(newX);
            object.setCenterY(newY);
            collisionLabel.setText(""); // Wyczyszczenie komunikatu o kolizji
            updatePositionLabels(); // Aktualizacja etykiet z położeniem
            updateDistanceLabel(distance); // Aktualizacja etykiety z przebytą drogą
            updateColorLabel((Color) object.getFill()); // Aktualizacja etykiety z kolorem
        } else {
            collisionLabel.setText("Stłuczka!"); // Komunikat o kolizji
        }
    }

    private void changeColorAndSpeed() {
        if (object.getFill() == Color.BLUE) {
            object.setFill(Color.RED);
        } else if (object.getFill() == Color.RED) {
            object.setFill(Color.YELLOW);
        } else if (object.getFill() == Color.YELLOW) {
            object.setFill(Color.GREEN);
        } else {
            object.setFill(Color.BLUE);
        }
    }

    private boolean checkCollision(double x, double y) {
        for (Rectangle obstacle : obstacles) {
            if (object.getBoundsInParent().intersects(obstacle.getBoundsInParent())) {
                return true; // Kolizja z przeszkodą
            }
        }
        return false; // Brak kolizji
    }

    private double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
