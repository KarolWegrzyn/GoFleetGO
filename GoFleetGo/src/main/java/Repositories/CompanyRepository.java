package Repositories;

import Classes.Company;
import Managers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyRepository {

    public void insertCompany(Company company) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO Company (Name, Subscription_ID) VALUES (?, ?)")) {
            preparedStatement.setString(1, company.getName());
            preparedStatement.setInt(2, company.getSubscriptionID());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Company inserted successfully.");
            } else {
                System.out.println("Failed to insert company.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Company findCompanyById(int companyID) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Company WHERE Company_ID = ?")) {
            preparedStatement.setInt(1, companyID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("Company_ID");
                String name = resultSet.getString("Name");
                int subscriptionID = resultSet.getInt("Subscription_ID");

                return new Company(id, name, subscriptionID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Add other methods as needed

}
