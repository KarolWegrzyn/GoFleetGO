package org.example.gofleetgo;

import org.example.gofleetgo.User;
import org.example.gofleetgo.UserRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseController {
    // Dane dostępowe do bazy danych
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gofleetgo";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Metoda do nawiązywania połączenia
    public static Connection connect() {
        try {
            // Rejestrowanie sterownika JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Nawiązywanie połączenia
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Connected to the database");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Metoda do zamykania połączenia
    public static void disconnect(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Disconnected from the database");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //metoda do sprawdzania poprawnosci logowania
    public boolean isDataLoginCorrect(String login, String password)
    {


        UserRepository sampleUser = new UserRepository();
        User sampUser = sampleUser.findUserByUsername(login);

        if(password == sampUser.getPassword())
        {
            return true;
        }
   




        return true;
    }

    public Boolean correctLoginAndPasswordBasedonLogin(String login)
    {

        return true;
    }

}
