package com.volunteeride.model;

/**
 * Created by ayazlakdawala on 9/7/15.
 */
public class Vehicle extends BaseModelObject {

    private String make;
    private String model;
    private int totalRiderCapacity;
    private VehicleType type;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }



    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getTotalRiderCapacity() {
        return totalRiderCapacity;
    }

    public void setTotalRiderCapacity(int totalRiderCapacity) {
        this.totalRiderCapacity = totalRiderCapacity;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }
}
