package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Company {
    private int companyID;
    private String name;
    private int subscriptionID;

    public Company(int companyID, String name, int subscriptionID) {
        this.companyID = companyID;
        this.name = name;
        this.subscriptionID = subscriptionID;
    }

    // Getters and setters

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSubscriptionID() {
        return subscriptionID;
    }

    public void setSubscriptionID(int subscriptionID) {
        this.subscriptionID = subscriptionID;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyID=" + companyID +
                ", name='" + name + '\'' +
                ", subscriptionID=" + subscriptionID +
                '}';
    }
}

