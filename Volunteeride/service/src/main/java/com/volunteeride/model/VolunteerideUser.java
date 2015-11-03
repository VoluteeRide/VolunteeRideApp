package com.volunteeride.model;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Iterator;
import java.util.List;

/**
 * This class represents Volunteeride User Model Object
 *
 * Created by ayazlakdawala on 9/7/15.
 */
@Document
public class VolunteerideUser extends BaseModelObject{

    @Indexed(unique = true)
    private String username;

    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<Vehicle> ownedVehicles;
    private ObjectId centerId;
    
    private List<UserRoleEnum> userRoles;

    /**
     * Utility method to return Comma separated list of Roles assigned to the User
     * Reference https://github.com/jthoms/spring-security-mongodb/blob/master/src/main/java/com/sustia/domain/UserAccount.java
     * @return Comma Seperated String of User Roles
     */
    public String getCommaSeperatedRoles() {
        StringBuilder sb = new StringBuilder();
        for (Iterator<UserRoleEnum> iter = this.userRoles.iterator(); iter.hasNext(); )
        {
            sb.append(iter.next().name());
            if (iter.hasNext()) {
                sb.append(',');
            }
        }
        return sb.toString();
    }

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

    public List<UserRoleEnum> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRoleEnum> userRoles) {
        this.userRoles = userRoles;
    }
}
