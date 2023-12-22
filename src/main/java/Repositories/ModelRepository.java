package Repositories;

import Classes.Model;
import Managers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ModelRepository {

    public void insertModel(Model model) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Model (Brand, Engine, Fueal_capacity, Year_of_production) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setString(1, model.getBrand());
            preparedStatement.setDouble(2, model.getEngine());
            preparedStatement.setDouble(3, model.getFuelCapacity());
            preparedStatement.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(model.getYearOfProduction()));

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Model inserted successfully.");
            } else {
                System.out.println("Failed to insert model.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Model findModelById(int modelID) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Model WHERE Model_ID = ?")) {
            preparedStatement.setInt(1, modelID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("Model_ID");
                String brand = resultSet.getString("Brand");
                double engine = resultSet.getDouble("Engine");
                double fuelCapacity = resultSet.getDouble("Fueal_capacity");
                java.util.Date yearOfProduction = resultSet.getDate("Year_of_production");

                return new Model(id, brand, engine, fuelCapacity, yearOfProduction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Add other methods as needed

}

