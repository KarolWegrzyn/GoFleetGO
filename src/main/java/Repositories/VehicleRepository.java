package Repositories;

import Classes.Vehicle;
import Managers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleRepository {

    public void insertVehicle(Vehicle vehicle) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Vehicle (Model_ID, Location) VALUES (?, ?)")) {
            preparedStatement.setInt(1, vehicle.getModelID());
            preparedStatement.setString(2, vehicle.getLocation());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Vehicle inserted successfully.");
            } else {
                System.out.println("Failed to insert vehicle.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vehicle findVehicleById(int vehicleID) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Vehicle WHERE Vechicle_ID = ?")) {
            preparedStatement.setInt(1, vehicleID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("Vechicle_ID");
                int modelID = resultSet.getInt("Model_ID");
                String location = resultSet.getString("Location");

                return new Vehicle(id, modelID, location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Add other methods as needed

}

