package Classes;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Route implements Serializable {
    private int routeID;
    private int startRow;
    private int startColumn;
    private int finishRow;
    private int finishColumn;
    private double distance;

    public Route(int routeID, int startRow, int startColumn, int finishRow, int finishColumn, double distance) {
        this.routeID = routeID;
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.finishRow = finishRow;
        this.finishColumn = finishColumn;
        this.distance = distance;
    }

    public Route() {
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


    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public void setStartColumn(int startColumn) {
        this.startColumn = startColumn;
    }

    public int getFinishRow() {
        return finishRow;
    }

    public void setFinishRow(int finishRow) {
        this.finishRow = finishRow;
    }

    public int getFinishColumn() {
        return finishColumn;
    }

    public void setFinishColumn(int finishColumn) {
        this.finishColumn = finishColumn;
    }
}

