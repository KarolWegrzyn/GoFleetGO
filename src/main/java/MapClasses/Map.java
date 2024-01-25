package MapClasses;

import Classes.Vehicle;
import Repositories.VehicleRepository;

import java.util.ArrayList;
import java.util.Arrays;

public class Map{
    private static final int mapSize = 10;
    private static final int[] baseLocation = {2,2};
    static Position[][] map = new Position[mapSize][mapSize];
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
                } else if ( (row > mapSize/3 && row < mapSize/2) && (column > mapSize/3 && column < mapSize/2)) {
                    mapType = Position.EnumMapType.noParking;
                }

                map[row][column] = new Position(row, column, mapType, null);
            }
        }
    }
//    public void readMap() {
//        System.out.print("  |");
//        for (int i = 0; i < mapSize; i++) {
//            System.out.print(i + "|");
//        }
//        System.out.println();
//
//        for (int row = 0; row < mapSize; row++) {
//            System.out.print(row + "|");
//            for (int column = 0; column < mapSize; column++) {
//                Position position = map[row][column];
//                if (map[row][column].vehicleId == null){
//                    System.out.print(position.mapType + "|");
//                } else {
//                    System.out.print("car |");
//                }
//            }
//            System.out.println();
//        }
//    }
//
//    public void placeCar(int row, int column, Integer vehicleId) throws Exception {
//        VehicleRepository vehicleRepository = new VehicleRepository();
//        Vehicle vehicle = vehicleRepository.findVehicleById(vehicleId);
//        if (vehicle == null){
//            throw new Exception("placeCarException: vehicle not found!");
//        }
//        if (vehicle.getRow() != baseLocation[0] || vehicle.getColumn() != baseLocation[1]){
//            throw new Exception("placeCarException: vehicle is not in the base!");
//        }
//        map[row][column].vehicleId = vehicleId;
//        vehicleRepository.updateLocation(vehicleId,row,column);
//        vehicleRepository.updateStatus(vehicleId, Vehicle.VehicleStatus.free);
//    }
//
//    public void moveVehicleToBase(Integer vehicleId) throws Exception {
//        VehicleRepository vehicleRepository = new VehicleRepository();
//        Vehicle vehicle = vehicleRepository.findVehicleById(vehicleId);
//        if (vehicle == null){
//            throw new Exception("moveVehicleToBaseException: vehicle not found!");
//        }
//        if (vehicle.getRow() == baseLocation[0] || vehicle.getColumn() == baseLocation[1]){
//            throw new Exception("moveVehicleToBaseException: vehicle is already in the base!");
//        }
//        if (!vehicle.getStatus().equals(Vehicle.VehicleStatus.free)){
//            throw new Exception("moveVehicleToBaseException: vehicle is in use!");
//        }
//        map[vehicle.getRow()][vehicle.getColumn()].vehicleId = null;
//        vehicleRepository.updateLocation(vehicleId,baseLocation[0],baseLocation[1]);
//        vehicleRepository.updateStatus(vehicleId, Vehicle.VehicleStatus.disabled);
//    }
    public static int getMapSize(){
        return mapSize;
    }

    public static Position.EnumMapType getPositionType(int row, int column){
        return map[row][column].mapType;
    }
}
