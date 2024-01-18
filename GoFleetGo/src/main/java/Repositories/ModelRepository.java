package Repositories;

import Classes.Model;
import Managers.ConnectionManager;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ModelRepository {

    public void insertModel(Model model) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Model (Brand, Engine, Fuel_capacity, Year_of_production) VALUES (?, ?, ?, ?)")) {
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
                double fuelCapacity = resultSet.getDouble("Fuel_capacity");
                java.util.Date yearOfProduction = resultSet.getDate("Year_of_production");

                return new Model(id, brand, engine, fuelCapacity, yearOfProduction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Model> getAllDistinctModels() {
        List<Model> models = new ArrayList<>();

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT DISTINCT Model_ID, Brand, Engine, Fuel_capacity, Year_of_production FROM model");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int modelID = resultSet.getInt("Model_ID");
                String brand = resultSet.getString("Brand");
                double engine = resultSet.getDouble("Engine");
                double fuelCapacity = resultSet.getDouble("Fuel_capacity");
                Date yearOfProduction = resultSet.getDate("Year_of_production");

                Model model = new Model(modelID, brand, engine, fuelCapacity, yearOfProduction);
                models.add(model);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return models;
    }

    // Add other methods as needed

}

