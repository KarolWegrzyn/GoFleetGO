package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Review {
    private int reviewID;
    private int userID;
    private int vehicleID;
    private int rating;

    public Review(int reviewID, int userID, int vehicleID, int rating) {
        this.reviewID = reviewID;
        this.userID = userID;
        this.vehicleID = vehicleID;
        this.rating = rating;
    }

    // Getters and setters

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewID=" + reviewID +
                ", userID=" + userID +
                ", vehicleID=" + vehicleID +
                ", rating=" + rating +
                '}';
    }
}
