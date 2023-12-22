package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Route {
    private int routeID;

    public Route(int routeID) {
        this.routeID = routeID;
    }

    // Getters and setters

    public int getRouteID() {
        return routeID;
    }

    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeID=" + routeID +
                '}';
    }
}

