package Repositories;

import Classes.Reservation;
import Classes.Vehicle;
import Managers.ConnectionManager;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static Managers.ConnectionManager.getConnection;

public class ReservationRepository {

    public int insertReservation(int currentUserID, int vehicleID, int durationInMinutes) {
        int generatedId = -1;

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Reservation (User_ID, Vehicle_ID, End_time, Status) VALUES (?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, currentUserID);
            preparedStatement.setInt(2, vehicleID);
            LocalDateTime endTime = LocalDateTime.now().plusMinutes(durationInMinutes);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            preparedStatement.setString(3, endTime.format(formatter));
            preparedStatement.setString(4, String.valueOf(Reservation.ReservationStatus.active));

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Retrieve the generated keys
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                        System.out.println("Reservation inserted successfully. Generated ID: " + generatedId);
                    } else {
                        System.out.println("Failed to retrieve generated ID.");
                    }
                }
            } else {
                System.out.println("Failed to insert reservation.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }


    public static Reservation findReservationById(int reservationID) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Reservation WHERE Reservation_ID = ?")) {
            preparedStatement.setInt(1, reservationID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("Reservation_ID");
                int userID = resultSet.getInt("User_ID");
                int vehicleID = resultSet.getInt("Vechicle_ID");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime endTime = resultSet.getTimestamp("End_time").toLocalDateTime();
                Reservation.ReservationStatus status = Reservation.ReservationStatus.valueOf(resultSet.getString("Status"));

                return new Reservation(id, userID, vehicleID, endTime, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void updateReservationAndLinkedVehicleState() throws SQLException {

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Reservation WHERE End_time <= NOW() AND Status = 'finished'")) {
            VehicleRepository vehicleRepository = new VehicleRepository();
            ReservationRepository reservationRepository = new ReservationRepository();

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("Reservation_ID");
                int userID = resultSet.getInt("User_ID");
                int vehicleID = resultSet.getInt("Vechicle_ID");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime endTime = resultSet.getTimestamp("End_time").toLocalDateTime();
                Reservation.ReservationStatus status = Reservation.ReservationStatus.valueOf(resultSet.getString("Status"));

                vehicleRepository.updateStatus(vehicleID, Vehicle.VehicleStatus.free);
                reservationRepository.updateStatus(id, Reservation.ReservationStatus.finished);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStatus(int reservationID, Reservation.ReservationStatus status) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE Reservation SET Status = '?' WHERE Reservation_ID = '?'")) {

            preparedStatement.setString(1, String.valueOf(status));
            preparedStatement.setInt(2, reservationID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Reservation status updated successfully.");
            } else {
                System.out.println("Failed to update reservation status. Reservation not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Add other methods as needed

}

