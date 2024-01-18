package Repositories;

import Classes.Ride;
import Classes.Route;
import Managers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteRepository {

    public void insertRoute(Route route) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Route () VALUES ()")) {
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Route inserted successfully.");
            } else {
                System.out.println("Failed to insert route.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Route findRouteById(int routeID) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Route WHERE Route_ID = ?")) {
            preparedStatement.setInt(1, routeID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("Route_ID");
                int startRow = resultSet.getInt("Start_Row");
                int startColumn = resultSet.getInt("Start_Column");
                int finishRow = resultSet.getInt("Finish_Row");
                int finishColumn = resultSet.getInt("Finish_Column");
                double distance = resultSet.getDouble("Distance");

                return new Route(id, startRow, startColumn, finishRow, finishColumn, distance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static int createNewRoute(int[] startPoint) {
        int generatedId = -1;

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Route (Start_Point_Row, Start_Point_Column) VALUES (?, ?")) {
            preparedStatement.setInt(1, startPoint[0]);
            preparedStatement.setInt(2, startPoint[1]);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                    generatedId = generatedKeys.getInt(1);
                    System.out.println("Ride inserted successfully.");
                    return generatedId;
                }
            }
            throw new Exception("Failed to insert route.");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    // Add other methods as needed

}

