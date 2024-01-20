package UI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class GetUserExample {
    private Boolean isLogged = null;

    public Boolean getLogged() {
        return isLogged;
    }

    public  void connection(String userNameToSearch, String user_pas) {
        // Parametry połączenia z bazą danych MySQL
        String url = "jdbc:mysql://localhost:3306/gofleetgo";
        String user = "root";
        String password = "";
        String user_Name="";
        String user_Correct_password ="";


        try {
            // Rejestracja sterownika JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Nawiązanie połączenia z bazą danych
            Connection connection = DriverManager.getConnection(url, user, password);

            // Pobranie użytkownika o ID równym 1
            //int userIdToRetrieve = 1;
            //String selectQuery = "SELECT * FROM users WHERE user_id = ?";

            // Wyszukiwanie użytkownika po nazwie użytkownika
            //String userNameToSearch = user_nam;
            User foundUser = getUserByUsername(connection, userNameToSearch);

            if (foundUser != null) {
                user_Name = foundUser.getUsername();
                user_Correct_password = foundUser.getPassword();

                if(Objects.equals(user_pas, user_Correct_password))
                {
                    System.out.println("Pomyslnie zalogowano!");
                    isLogged = true;
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
    }
    // Metoda do wyszukiwania użytkownika po nazwie użytkownika
    private static User getUserByUsername(Connection connection, String userName) throws SQLException {
        String query = "SELECT * FROM user WHERE Username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("user_id");
                    String userPassword = resultSet.getString("password");
                    return new User(userId, userName, userPassword, 1,null);
                }
            }
        }
        return null;
    }

}
//System.out.println("Znaleziono użytkownika:");
//System.out.println("User ID: " + foundUser.getUserId());
//System.out.println("User Name: " + user_Name);
//System.out.println("Password: " + user_Correct_password);
//System.out.println("Pomyslnie dopisano poprawne dane!");
    /*

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, userIdToRetrieve);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Przetwarzanie wyników zapytania
                    while (resultSet.next()) {
                        int userId = resultSet.getInt("user_id");
                        String userName = resultSet.getString("user_name");
                        String userPassword = resultSet.getString("password");

                        // Tutaj możesz użyć pobranych informacji o użytkowniku
                        System.out.println("User ID: " + userId);
                        System.out.println("User Name: " + userName);
                        System.out.println("Password: " + userPassword);
                    }
                }
            }

             */
            /*
            // Dodanie nowego użytkownika
            int newUserId = 3;
            String newUserName = "new_user";
            String newPassword = "new_password";
            String insertQuery = "INSERT INTO users (user_id, user_name, password) VALUES (?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, newUserId);
                preparedStatement.setString(2, newUserName);
                preparedStatement.setString(3, newPassword);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Nowy użytkownik dodany pomyślnie.");
                } else {
                    System.out.println("Dodanie nowego użytkownika nie powiodło się.");
                }
            }
            */
// Klasa reprezentująca użytkownika
    /*
    private static class User {
        private int userId;
        private String userName;
        private String password;

        public User(int userId, String userName, String password) {
            this.userId = userId;
            this.userName = userName;
            this.password = password;
        }

        public int getUserId() {
            return userId;
        }

        public String getUserName() {
            return userName;
        }

        public String getPassword() {
            return password;
        }
    }

     */