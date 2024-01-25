package Classes;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Route implements Serializable {
    private int routeID;
    private double startRow;
    private double startColumn;
    private double finishRow;

    private double distance;
    public int getRouteID() {
        return routeID;
    }

    public void setRouteID(int routeID) {
        this.routeID = routeID;
    }

    public double getStartRow() {
        return startRow;
    }

    public void setStartRow(double startRow) {
        this.startRow = startRow;
    }

    public double getStartColumn() {
        return startColumn;
    }

    public void setStartColumn(double startColumn) {
        this.startColumn = startColumn;
    }

    public double getFinishRow() {
        return finishRow;
    }

    public void setFinishRow(double finishRow) {
        this.finishRow = finishRow;
    }

    public double getFinishColumn() {
        return finishColumn;
    }

    public void setFinishColumn(double finishColumn) {
        this.finishColumn = finishColumn;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    private double finishColumn;

    public Route(int routeID, double startRow, double startColumn, double finishRow, double finishColumn, double distance) {
        this.routeID = routeID;
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.finishRow = finishRow;
        this.finishColumn = finishColumn;
        this.distance = distance;
    }

    public Route(){};

}

