package MapClasses;

import Classes.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;

public class Map{
    private final int mapSize = 50;
    Position[][] map = new Position[mapSize][mapSize];
    //int[][] map = new int[mapSize][mapSize];

    public Map(){
        initializeMap();
    }

    public void initializeMap(){
        int value  = 1;
        for (int row = 0; row < mapSize; row++){
            for (int column = 0; column < mapSize; column++){
                Position.EnumMapType mapType = Position.EnumMapType.road;
                if ( (row % 2 == 0) && (column % 2 == 0) ) {
                    mapType = Position.EnumMapType.building;
                } else if ( (row > 20 && row < 30) && (column > 20 && column < 30)) {
                    mapType = Position.EnumMapType.noParking;
                }

                map[row][column] = new Position(row, column, mapType, null);
            }
        }
    }
    public void readMap() {
        System.out.print("  |");
        for (int i = 0; i < mapSize; i++) {
            System.out.print(i + "|");
        }
        System.out.println();

        for (int row = 0; row < mapSize; row++) {
            System.out.print(row + "|");
            for (int column = 0; column < mapSize; column++) {
                Position position = map[row][column];
                if (map[row][column].vehicleId == null){
                    System.out.print(position.mapType + "|");
                } else {
                    System.out.print("car |");
                }
            }
            System.out.println();
        }
    }

    public boolean placeCar(int row, int column, Integer vehicleId) {
        map[row][column].vehicleId = vehicleId;
        return true;
    }
}
