package com.volunteeride.model;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;


/**
 * Created by ayazlakdawala on 9/7/15.
 */
public class BaseModelObject {

    @Id
    private String id;
    private DateTime createdDatetime;

    @Version
    private Long version;

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

    public DateTime getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(DateTime createdDatetime) {
        this.createdDatetime = createdDatetime;
    }
}
