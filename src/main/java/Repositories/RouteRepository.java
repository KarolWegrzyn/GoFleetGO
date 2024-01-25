package Repositories;

import Classes.Ride;
import Classes.Route;
import Managers.ConnectionManager;

import java.sql.*;

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

    public static void updateRoute(int routeID, double finishRow, double finishColumn, int distance) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE Route SET `Finish_Row` = ?, `Finish_Column` = ?, `Distance` = ? WHERE Route_ID = ?")) {

            preparedStatement.setDouble(1, finishRow);
            preparedStatement.setDouble(2, finishColumn);
            preparedStatement.setInt(3, distance);
            preparedStatement.setInt(4, routeID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Route updated successfully.");
            } else {
                System.out.println("Failed to update route.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int createNewRoute(double[] startPoint) {
        int generatedId = -1;

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Route (Start_Row, Start_Column, Finish_Row, Finish_Column, Distance) VALUES (?, ?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDouble(1, startPoint[0]);
            preparedStatement.setDouble(2, startPoint[1]);
            preparedStatement.setNull(3, java.sql.Types.INTEGER);
            preparedStatement.setNull(4, java.sql.Types.INTEGER);
            preparedStatement.setNull(5, java.sql.Types.INTEGER);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                // Retrieve the generated keys
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                        System.out.println("Route inserted successfully. Generated ID: " + generatedId);
                    } else {
                        System.out.println("Failed to retrieve generated ID.");
                    }
                }
            } else {
                System.out.println("Failed to insert route.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return generatedId;
    }

    // Add other methods as needed

}

