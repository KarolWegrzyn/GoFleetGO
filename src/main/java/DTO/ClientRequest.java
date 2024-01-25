package DTO;

import java.io.Serial;
import java.io.Serializable;

public class ClientRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String action;
    private Serializable data;
    private Integer privateToken;

    public ClientRequest() {
    }

    public ClientRequest(String action, Serializable data) {
        this.action = action;
        this.data = data;
    }

    public ClientRequest(String action, Serializable data, Integer privateToken) {
        this.action = action;
        this.data = data;
        this.privateToken = privateToken;
    }

    public String getAction() {
        return action;
    }

    public Serializable getData() {
        return data;
    }

    public Integer getPrivateToken() {
        return privateToken;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setData(Serializable data) {
        this.data = data;
    }

    public void setPrivateToken(Integer privateToken) {
        this.privateToken = privateToken;
    }
}
