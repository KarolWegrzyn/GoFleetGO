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

