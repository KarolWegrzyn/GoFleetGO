package Repositories;

import Classes.User;
import Managers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    public void insertUser(User user) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO User (Username, Password, Company_ID, Email) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getCompanyID());
            preparedStatement.setString(4, user.getEmail());

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

    public User findUserById(int userID) {
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

    // Add other methods as needed

}

