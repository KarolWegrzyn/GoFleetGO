package Repositories;

import Classes.Ride;
import Managers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RideRepository {

    public void insertRide(Ride ride) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Ride (User_ID, Vechicle_ID, Reservation_ID, Route_ID) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setInt(1, ride.getUserID());
            preparedStatement.setInt(2, ride.getVehicleID());
            preparedStatement.setInt(3, ride.getReservationID());
            preparedStatement.setInt(4, ride.getRouteID());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Ride inserted successfully.");
            } else {
                System.out.println("Failed to insert ride.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Ride createNewRide(int userId, int vehicleId, Integer reservationId, int routeId) {
        int generatedId = -1;

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Reservation (User_ID, Vechicle_ID, Reservation_ID, Route_ID) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, vehicleId);
            if (reservationId == null){
                preparedStatement.setNull(3, java.sql.Types.INTEGER);
            } else {
                preparedStatement.setInt(3, reservationId);
            }
            preparedStatement.setInt(4, routeId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                    generatedId = generatedKeys.getInt(1);
                    System.out.println("Ride inserted successfully.");
                    return new Ride(generatedId, userId, vehicleId, reservationId, routeId);
                }
            } else {
                System.out.println("Failed to insert ride.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Ride findRideById(int rideID) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Ride WHERE Ride_ID = ?")) {
            preparedStatement.setInt(1, rideID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("Ride_ID");
                int userID = resultSet.getInt("User_ID");
                int vehicleID = resultSet.getInt("Vechicle_ID");
                int reservationID = resultSet.getInt("Reservation_ID");
                int routeID = resultSet.getInt("Route_ID");

                return new Ride(id, userID, vehicleID, reservationID, routeID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Add other methods as needed

}

