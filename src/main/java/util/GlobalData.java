package util;

public class GlobalData {
    private static Integer userId;
    private static Integer currentRideId;

    public static Integer getUserId() {
        return userId;
    }

    public static void setUserId(Integer userId) {
        GlobalData.userId = userId;
    }

    public static Integer getCurrentRideId() {
        return currentRideId;
    }

    public static void setCurrentRideId(Integer currentRideId) {
        GlobalData.currentRideId = currentRideId;
    }
}
