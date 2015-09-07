package com.volunteeride.model;

import java.util.List;

/**
 * Model class representing religious center
 *
 * Created by ayazlakdawala on 9/1/15.
 */
public class Center extends BaseModelObject {

    private Location location;
    private List<Location> pickUpLocations;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Location> getPickUpLocations() {
        return pickUpLocations;
    }

    public void setPickUpLocations(List<Location> pickUpLocations) {
        this.pickUpLocations = pickUpLocations;
    }
}
