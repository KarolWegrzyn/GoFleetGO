package Repositories;

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

                return new Route(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Add other methods as needed

}

