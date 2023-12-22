package Repositories;

import Classes.Reservation;
import Managers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class ReservationRepository {

    public void insertReservation(Reservation reservation) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Reservation (User_ID, Vechicle_ID, Start_time) VALUES (?, ?, ?)")) {
            preparedStatement.setInt(1, reservation.getUserID());
            preparedStatement.setInt(2, reservation.getVehicleID());
            preparedStatement.setString(3, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(reservation.getStartTime()));

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Reservation inserted successfully.");
            } else {
                System.out.println("Failed to insert reservation.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Reservation findReservationById(int reservationID) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Reservation WHERE Reservation_ID = ?")) {
            preparedStatement.setInt(1, reservationID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("Reservation_ID");
                int userID = resultSet.getInt("User_ID");
                int vehicleID = resultSet.getInt("Vechicle_ID");
                java.util.Date startTime = resultSet.getTimestamp("Start_time");

                return new Reservation(id, userID, vehicleID, startTime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Add other methods as needed

}

