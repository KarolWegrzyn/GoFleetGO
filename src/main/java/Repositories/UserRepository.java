package Repositories;

import Classes.User;
import Managers.ConnectionManager;

import java.sql.*;

public class UserRepository {
    public static void insertUser(String username, String password, String email) {

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO User (Username, Password, Company_ID, Email, Balance) VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setNull(3, Types.INTEGER);

            preparedStatement.setString(4, email);
            preparedStatement.setDouble(5, 0);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User inserted successfully.");
            } else {
                System.out.println("Failed to insert user.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User findUserByUsername(String Username) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM User WHERE Username = ?")) {
            preparedStatement.setString(1, Username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("User_ID");
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");
                int companyID = resultSet.getInt("Company_ID");
                String email = resultSet.getString("Email");

                return new User(id, username, password, companyID, email);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static User findUserById(int userID) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM User WHERE User_ID = ?")) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("User_ID");
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");
                int companyID = resultSet.getInt("Company_ID");
                String email = resultSet.getString("Email");

                return new User(id, username, password, companyID, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static double getUserBalanceById(int userID) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT Balance FROM User WHERE User_ID = ?")) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble("Balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static void updateBalance(int userId, double amount) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE User SET Balance = Balance + ? WHERE User_ID = ?")) {

            preparedStatement.setDouble(1, amount);
            preparedStatement.setInt(2, userId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Balance updated successfully for User_ID: " + userId);
            } else {
                System.out.println("Failed to update balance for User_ID: " + userId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed, e.g., log or throw a custom exception
        }
    }

    // Add other methods as needed

}

