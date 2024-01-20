package Classes;

import MapClasses.Map;
import Repositories.*;

public class test {
    public static void main(String[] args) throws Exception {
        int currentUserID = 1;
        SubscriptionRepository subscriptionRepository = new SubscriptionRepository();
        ModelRepository modelRepository = new ModelRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();
        UserRepository userRepository = new UserRepository();

        Map map = new Map();
//        Model model1 = new Model(1, "Toyota Camry", 2.5, 60.0, new Date(2022, 5, 15));
//        Model model2 = new Model(2, "Honda Civic", 1.8, 55.0, new Date(2021, 8, 20));
//        Model model3 = new Model(3, "Ford Mustang", 5.0, 70.0, new Date(2023, 2, 10));
//        Model model4 = new Model(4, "Chevrolet Silverado", 6.2, 80.0, new Date(2022, 11, 5));
//        Model model5 = new Model(5, "Nissan Altima", 2.0, 58.0, new Date(2023, 6, 30));
//
//        modelRepository.insertModel(model1);
//        modelRepository.insertModel(model2);
//        modelRepository.insertModel(model3);
//        modelRepository.insertModel(model4);
//        modelRepository.insertModel(model5);
        map.moveVehicleToBase(1);
//        map.moveVehicleToBase(2);
//        map.moveVehicleToBase(3);
        map.placeCar(1,3,1);
//        map.placeCar(2,3,2);
//        map.placeCar(3,3,3);

//            int[][] array = new int[2][2];
//            array[0][1] = 1;
//        System.out.println(Arrays.deepToString(array));
        Vehicle vehicle = VehicleRepository.findVehicleById(3);
        Ride ride = Ride.createNewRide(currentUserID, vehicle);
        Thread.sleep(3000);
        Ride.stopRide(ride, vehicle);


//        userRepository.updateBalance(2, -11000000);
//        User user = new User(1, "123", "123", null, "mail");
//        insertUser(user);
//        makeReservation(currentUserID,3,1);
//        updateReservationAndLinkedVehicleState();
//        Reservation reservation  = new Reservation(currentUserID, 1, 15);

//        Subscription newSubscription = new Subscription(1, 59.99, 2000.00); // Assuming _ID is auto-incremented
//        subscriptionRepository.insertSubscription(newSubscription);

//        int id = 0;
//        Subscription result = subscriptionRepository.findSubscriptionById(id);
//        System.out.println(result.toString());
//        Vehicle vehicle1 = vehicleRepository.findVehicleById(1);
//        vehicle1.drive();
//        vehicle1.drive();
//        Vehicle vehicle2 = vehicleRepository.findVehicleById(3);
//        vehicle2.drive();

//        map.placeCar(5,3,2);
//        map.moveVehicleToBase(2);
//        ListAllModels();
//        mapa.readMap();
    }

    public static int getUserID(){
        return 1;
    }
}