package Classes;

import Managers.ConnectionManager;
import Repositories.UserRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class User {
    private int userID;
    private String username;
    private String password;
    private Integer companyID;
    private String email;
    private double balance;

    private Boolean isLogged = null;



    public User(int userID, String username, String password, Integer companyID, String email) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.companyID = companyID;
        this.email = email;
        this.balance = 0;
    }

    public User() {
    }

    public Integer fetchUser(String userNameToSearch, String user_pas) {
        String user_Name="";
        String user_Correct_password ="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = ConnectionManager.getConnection();

            User foundUser = UserRepository.findUserByUsername(userNameToSearch);

            if (foundUser != null) {
                user_Correct_password = foundUser.getPassword();

                if(Objects.equals(user_pas, user_Correct_password))
                {
                    System.out.println("Pomyslnie zalogowano!");
                    isLogged = true;
                    return foundUser.getUserID();
                }
                else
                {
                    System.out.println("Niepoprawne dane!");
                    isLogged = false;
                }
            } else {
                System.out.println("Nie znaleziono użytkownika o nazwie: " + userNameToSearch);
                isLogged = false;
            }
            // Zamykanie połączenia

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public Boolean getLogged() {
        return isLogged;
    }

    // Getters and setters

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCompanyID() {
        if (companyID == null) return null;
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", companyID=" + companyID +
                ", email='" + email + '\'' +
                '}';
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

