package Services;

import DTO.VehicleModelData;
import Repositories.VehicleRepository;

public class VehicleService {
    public static VehicleModelData getVehicleModelDataById(Integer vehicleId){
        return  VehicleRepository.findVehicleModelById(vehicleId);
    }

}
