package DTO;

import java.io.Serializable;

public class StartRideData implements Serializable {
    double row;
    double column;
    double price;

    public StartRideData(double row, double column, double price) {
        this.row = row;
        this.column = column;
        this.price = price;
    }

    public double getRow() {
        return row;
    }

    public void setRow(double row) {
        this.row = row;
    }

    public double getColumn() {
        return column;
    }

    public void setColumn(double column) {
        this.column = column;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
