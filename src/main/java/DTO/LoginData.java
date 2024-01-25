package DTO;

import java.io.Serializable;

public class LoginData implements Serializable {
    private String username;
    private String password;

    public LoginData(String login, String password) {
        this.username = login;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
