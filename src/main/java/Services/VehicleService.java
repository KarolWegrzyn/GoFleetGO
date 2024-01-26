package Services;

import DTO.StartRideData;
import DTO.VehicleModelData;
import Repositories.VehicleRepository;

public class VehicleService {
    public static VehicleModelData getVehicleModelDataById(Integer vehicleId){
        return  VehicleRepository.findVehicleModelById(vehicleId);
    }

    public static StartRideData getVehicleStartDataById(Integer vehicleId){
        return VehicleRepository.returnVehicleStartDataById(vehicleId);
    }
}
