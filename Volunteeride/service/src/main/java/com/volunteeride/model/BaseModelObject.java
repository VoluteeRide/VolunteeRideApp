package com.volunteeride.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;


/**
 * Created by ayazlakdawala on 9/7/15.
 */
public class BaseModelObject {

    @Id
    protected String id;


    /**
     * This field is inserted with current date time in database using CreatedDate annotation.
     * This field will be ignored during deserialization and rendered during serialization.
     * See the Json annotations on this property, its getters and setters for more details.
     */
    @JsonIgnore
    @CreatedDate
    protected DateTime createdDatetime;

    @Version
    protected Long version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @JsonProperty
    public DateTime getCreatedDatetime() {
        return createdDatetime;
    }

    @JsonIgnore
    public void setCreatedDatetime(DateTime createdDatetime) {
        this.createdDatetime = createdDatetime;
    }
}
