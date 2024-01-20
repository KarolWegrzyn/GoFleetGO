package Repositories;

import Classes.Vehicle;
import Managers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static Managers.ConnectionManager.getConnection;

public class VehicleRepository {

    public void insertVehicle(Vehicle vehicle) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Vehicle (Model_ID, Row, Column, Status, fuel_level) VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setInt(1, vehicle.getModelID());
            preparedStatement.setInt(2, vehicle.getRow());
            preparedStatement.setInt(3, vehicle.getColumn());
            preparedStatement.setInt(4, vehicle.getColumn());
            preparedStatement.setInt(5, vehicle.getColumn());

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

    public static Vehicle findVehicleById(int vehicleID) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Vehicle WHERE Vehicle_ID = ?")) {
            preparedStatement.setInt(1, vehicleID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("Vehicle_ID");
                int modelID = resultSet.getInt("Model_ID");
                int row = resultSet.getInt("Row");
                int column = resultSet.getInt("Column");
                Vehicle.VehicleStatus status = Vehicle.VehicleStatus.valueOf(resultSet.getString("Status"));
                float fuelLevel = resultSet.getFloat("fuel_level");

                return new Vehicle(id, modelID, row, column, status, fuelLevel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void updateStatus(int vehicleID, Vehicle.VehicleStatus status) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE vehicle SET `Status` = ? WHERE Vehicle_ID = ?")) {

            preparedStatement.setString(1, String.valueOf(status));
            preparedStatement.setInt(2, vehicleID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Vehicle status updated successfully.");
            } else {
                System.out.println("Failed to update vehicle status. Vehicle not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateLocation(int vehicleID, int newRow, int newColumn) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE vehicle SET `Row` = ?, `Column` = ? WHERE Vehicle_ID = ?")) {

            preparedStatement.setInt(1, newRow);
            preparedStatement.setInt(2, newColumn);
            preparedStatement.setInt(3, vehicleID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Vehicle location updated successfully.");
            } else {
                System.out.println("Failed to update vehicle location. Vehicle not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add other methods as needed

}

