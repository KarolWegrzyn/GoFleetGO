package Classes;

public class User {
    private int userID;
    private String username;
    private String password;
    private Integer companyID;
    private String email;
    private double balance;

    public User(int userID, String username, String password, Integer companyID, String email) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.companyID = companyID;
        this.email = email;
        this.balance = 0;
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

