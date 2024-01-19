package MapClasses;

public class Position {
    int row;
    int column;
    Integer vehicleId;
    EnumMapType mapType;
    public enum EnumMapType{
        road,
        building,
        noParking
    }

    public Position (int row, int column, EnumMapType type, Integer vehicleId) {
        this.row = row;
        this.column = column;
        this.mapType = type;
        this.vehicleId = vehicleId;
    }

    public String ToString(){
        return "r" + String.valueOf(row) + "c" + String.valueOf(column) + "t" + mapType + "id" + vehicleId;
    }
}
