package com.volunteeride.rest.resource.beans;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

/**
 * Created by ayazlakdawala on 10/13/15.
 */
public class PaginationAndSortingCriteriaBean {

    private @QueryParam("page") int page;

    private @DefaultValue("20") @QueryParam("size") int size;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
