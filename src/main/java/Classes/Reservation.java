package Classes;

import Repositories.ReservationRepository;
import Repositories.VehicleRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
    private int reservationID;
    private int userID;
    private int vehicleID;
    private LocalDateTime endTime;
    private ReservationStatus status;

    public enum ReservationStatus{
        active,
        finished
    }

    static ReservationRepository reservationRepository = new ReservationRepository();
    static VehicleRepository vehicleRepository = new VehicleRepository();
    public Reservation(int reservationID, int userID, int vehicleID, LocalDateTime endTime, ReservationStatus status) {
        this.reservationID = reservationID;
        this.userID = userID;
        this.vehicleID = vehicleID;
        this.endTime = endTime;
        this.status = status;
    }

    public Reservation(int userID, int vehicleID, int durationInMinutes) {
        this.userID = userID;
        this.vehicleID = vehicleID;
        LocalDateTime endTime = LocalDateTime.now().plusMinutes(durationInMinutes);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        endTime.format(formatter);
        this.endTime = endTime;
        this.status = ReservationStatus.active;
    }

//    public static void updateReservationAndLinkedVehicleState() throws SQLException {
//        ResultSet resultSet = reservationRepository.findReservationToUpdate();
//        if (resultSet.next()) {
//            int id = resultSet.getInt("Reservation_ID");
//            int userID = resultSet.getInt("User_ID");
//            int vehicleID = resultSet.getInt("Vechicle_ID");
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            LocalDateTime endTime = resultSet.getTimestamp("End_time").toLocalDateTime();
//            Reservation.ReservationStatus status = Reservation.ReservationStatus.valueOf(resultSet.getString("Status"));
//
//            vehicleRepository.updateStatus(vehicleID, Vehicle.VehicleStatus.free);
//            reservationRepository.updateStatus(id, ReservationStatus.finished);
//        }
//    }

    public static Reservation makeReservation(int currentUserID, int vehicleID, int duration) throws Exception {
        Vehicle vehicle = vehicleRepository.findVehicleById(vehicleID);
        if (!vehicle.getStatus().equals(Vehicle.VehicleStatus.free)){
            throw new Exception("Vehicle is not available");
        }

        Reservation reservation = new Reservation(currentUserID, vehicleID, duration);
        int reservationId = reservationRepository.insertReservation(currentUserID, vehicleID, duration);
        reservation.setReservationID(reservationId);
        vehicleRepository.updateStatus(vehicleID, Vehicle.VehicleStatus.reserved);
        return reservation;
    }
    // Getters and setters

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationID=" + reservationID +
                ", userID=" + userID +
                ", vehicleID=" + vehicleID +
                ", endTime=" + endTime +
                '}';
    }
}

