package Repositories;

import Classes.Subscription;
import Managers.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscriptionRepository {
    public Subscription findSubscriptionById(int subscriptionId) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Subscription WHERE Subscription_ID = ?")) {
            preparedStatement.setInt(1, subscriptionId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("_ID");
                double cost = resultSet.getDouble("Cost");
                double funds = resultSet.getDouble("Funds");

                return new Subscription(id, cost, funds);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void insertSubscription(Subscription subscription) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Subscription (Subscription_ID, Cost, Funds) VALUES (?, ?, ?)")) {
            preparedStatement.setDouble(1, subscription.getSubscriptionId());
            preparedStatement.setDouble(2, subscription.getCost());
            preparedStatement.setDouble(3, subscription.getFunds());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Subscription inserted successfully.");
            } else {
                System.out.println("Failed to insert subscription.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Dodaj inne metody związane z operacjami na bazie danych dla tabeli Subscription
}