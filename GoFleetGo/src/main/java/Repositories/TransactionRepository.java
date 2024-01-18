package Repositories;

import Classes.Transaction;
import Managers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class TransactionRepository {

    public void insertTransaction(Transaction transaction) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Transaction (User_ID, Vechicle_ID, Ride_ID, Date) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setInt(1, transaction.getUserID());
            preparedStatement.setInt(2, transaction.getVehicleID());
            preparedStatement.setInt(3, transaction.getRideID());
            preparedStatement.setString(4, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(transaction.getDate()));

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Transaction inserted successfully.");
            } else {
                System.out.println("Failed to insert transaction.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Transaction findTransactionById(int transactionID) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Transaction WHERE Transaction_ID = ?")) {
            preparedStatement.setInt(1, transactionID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("Transaction_ID");
                int userID = resultSet.getInt("User_ID");
                int vehicleID = resultSet.getInt("Vechicle_ID");
                int rideID = resultSet.getInt("Ride_ID");
                java.util.Date date = resultSet.getTimestamp("Date");

                return new Transaction(id, userID, vehicleID, rideID, date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Add other methods as needed

}

