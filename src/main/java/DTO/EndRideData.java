package DTO;

import Classes.Route;

import java.io.Serializable;

public class EndRideData implements Serializable {
    private Integer rideId;
    private Route route;

    public EndRideData() {
    }

    public Integer getRideId() {
        return rideId;
    }

    public void setRideId(Integer rideId) {
        this.rideId = rideId;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
