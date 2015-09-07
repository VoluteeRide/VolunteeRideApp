package com.volunteeride.model;

import org.joda.time.DateTime;

/**
 * Created by ayazlakdawala on 9/7/15.
 */
public class BaseModelObject {

    private Long id;
    private DateTime createdDatetime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(DateTime createdDatetime) {
        this.createdDatetime = createdDatetime;
    }
}
