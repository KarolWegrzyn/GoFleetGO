package Repositories;

import Classes.Review;
import Managers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewRepository {

    public void insertReview(Review review) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Review (User_ID, Vechicle_ID, Rating) VALUES (?, ?, ?)")) {
            preparedStatement.setInt(1, review.getUserID());
            preparedStatement.setInt(2, review.getVehicleID());
            preparedStatement.setInt(3, review.getRating());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Review inserted successfully.");
            } else {
                System.out.println("Failed to insert review.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Review findReviewById(int reviewID) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Review WHERE Review_ID = ?")) {
            preparedStatement.setInt(1, reviewID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("Review_ID");
                int userID = resultSet.getInt("User_ID");
                int vehicleID = resultSet.getInt("Vechicle_ID");
                int rating = resultSet.getInt("Rating");

                return new Review(id, userID, vehicleID, rating);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Add other methods as needed

}

