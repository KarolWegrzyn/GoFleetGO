package UI;

import Classes.Ride;
import Classes.Route;
import DTO.ClientRequest;
import DTO.EndRideData;
import DTO.ServerResponse;
import Repositories.RideRepository;
import Services.RideService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import util.GlobalData;
import util.NetworkClient;

import java.util.ArrayList;
import java.util.List;

public class MovingObjectWithObstacles {
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
    private double endX;
    private double endY;
    private double endDistance;
    private boolean journeyEnded = false;
    private Text summaryText;
    private double totalDistance = 0.0;
    private int id;
    Pane mapPane = new Pane();

    //Delete afeter testing
    Integer vehicleId = 1;
    public Scene start() throws Exception {
        ClientRequest clientRequest = new ClientRequest();
        clientRequest.setData(vehicleId);
        clientRequest.setPrivateToken(GlobalData.getUserId());

        clientRequest.setAction("createNewRide");
        ServerResponse serverResponse = NetworkClient.sendRequest(clientRequest);

        initializeSummaryText();

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
        //id=3;
        object = new Circle(OBJECT_RADIUS, Color.GREEN); // Startujemy od koloru zielonego
        //setColorById(3);

        //changeColorAndSpeed(3);
        //placeObjectOnMap(50, 50); // Początkowa pozycja obiektu
        setInitialPosition(50,50);
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

        Scene scene = new Scene(mapPane, 700, 500); // Zmiana rozmiarów scen

        // Ustawienie fokusu na mapie, aby obsługa zdarzeń klawiatury działała
        mapPane.requestFocus();
        return scene ;
    }

    public void setId(int id_)
    {
        id = id_;
    }
    public void setInitialPosition(double x, double y) {
        placeObjectOnMap(x, y);
    }

    public void endJourney() {
        endX = object.getCenterX();
        endY = object.getCenterY();
        endDistance = totalDistance;
        journeyEnded = true; // Ustawienie flagi na true po zakończeniu przejazdu

        ClientRequest clientRequest = new ClientRequest();
        Route route = new Route();
        route.setFinishRow(endX);
        route.setFinishColumn(endY);
        route.setDistance(endDistance);

        clientRequest.setData(route);
        clientRequest.setPrivateToken(GlobalData.getUserId());
        clientRequest.setAction("endRide");

        NetworkClient.sendRequest(clientRequest);

        hideAllObjects();
        showSummary();
    }
    private void initializeSummaryText() {
        summaryText = new Text();
        summaryText.setLayoutX(200); // Ustawienie połowy szerokości sceny
        summaryText.setLayoutY(200); // Ustawienie połowy wysokości sceny
        summaryText.setFont(new Font(20)); // Ustawienie większej czcionki
        summaryText.setTextAlignment(TextAlignment.CENTER); // Wyśrodkowanie tekstu
        summaryText.textAlignmentProperty(); // Wyśrodkowanie tekstu
        mapPane.getChildren().add(summaryText);
        summaryText.setVisible(false); // Początkowo tekst jest niewidoczny
    }

    private void showSummary() {
        String summary = String.format("Podsumowanie trasy:\n \n" +
                "Początkowa pozycja: (%.2f, %.2f)\n" +
                "Końcowa pozycja: (%.2f, %.2f)\n" +
                "Przebyty dystans: %.2f", 50.0, 50.0, endX, endY, endDistance);

        summaryText.setText(summary);
        summaryText.setVisible(true);
    }
    private void hideAllObjects() {
        object.setVisible(false); // Ukryj kółko

        for (Rectangle obstacle : obstacles) {
            obstacle.setVisible(false); // Ukryj przeszkody
        }

        collisionLabel.setVisible(false); // Ukryj etykietę o kolizji
        xLabel.setVisible(false); // Ukryj etykietę X
        yLabel.setVisible(false); // Ukryj etykietę Y
        distanceLabel.setVisible(false); // Ukryj etykietę z przebytą drogą
        colorLabel.setVisible(false); // Ukryj etykietę z kolorem
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() {
        return endY;
    }

    public double getEndDistance() {
        return endDistance;
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
        if (journeyEnded) {
            return; // Jeśli przejazd został zakończony, przerwij obsługę klawisza
        }

        if (code == KeyCode.K) {
            // Zakończ przejazd po wciśnięciu klawisza 'k'
            endJourney();
            System.out.println("Końcowy Y:"+endY);
            System.out.println("Końcowy X:"+endX);
            System.out.println("Końcowy Dystans:"+endDistance);
        } else {
            // Reszta kodu obsługująca ruch samochodu
            double newX = object.getCenterX();
            double newY = object.getCenterY();

            double speed;

            changeColorAndSpeed(id);

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
                    //changeColorAndSpeed();
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
    }

    private void changeColorAndSpeed(int colorIndex) {
        // Domyślnie ustaw kolor na zielony
        Color newColor = Color.GREEN;
        double newSpeed = OBJECT_SPEED_GREEN;

        // Wybierz kolor i prędkość na podstawie przekazanego indeksu
        switch (colorIndex) {
            case 0:
                newColor = Color.GREEN;
                newSpeed = OBJECT_SPEED_GREEN;
                break;
            case 1:
                newColor = Color.BLUE;
                newSpeed = OBJECT_SPEED_BLUE;
                break;
            case 2:
                newColor = Color.RED;
                newSpeed = OBJECT_SPEED_RED;
                break;
            case 3:
                newColor = Color.YELLOW;
                newSpeed = OBJECT_SPEED_YELLOW;
                break;
            default:
                // Domyślnie ustaw kolor na zielony, gdy indeks jest nieprawidłowy
                newColor = Color.GREEN;
                newSpeed = OBJECT_SPEED_GREEN;
                break;
        }

        object.setFill(newColor); // Ustaw nowy kolor
        updateColorLabel(newColor); // Aktualizuj etykietę z kolorem
        // Dodatkowo można by tutaj ustawić nową prędkość, jeśli to potrzebne
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
}
