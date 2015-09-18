package com.volunteeride.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * This class represents User Model Object
 *
 * Created by ayazlakdawala on 9/7/15.
 */
@Document
public class User extends BaseModelObject{

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<Vehicle> ownedVehicles;
    private ObjectId centerId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Vehicle> getOwnedVehicles() {
        return ownedVehicles;
    }

    public void setOwnedVehicles(List<Vehicle> ownedVehicles) {
        this.ownedVehicles = ownedVehicles;
    }

    public ObjectId getCenterId() {
        return centerId;
    }

    public void setCenterId(ObjectId centerId) {
        this.centerId = centerId;
    }
}
