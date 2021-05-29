package com.novko.api.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Query {

    @NotNull
    private Integer page;

    @NotNull
    private Integer size;

    @NotNull
    @NotEmpty
    private String sortProperty;

    @NotNull
    @NotEmpty
    private String sortDirection;

    private Filter filter;

    public Query() {}

    public Query(Integer page, Integer size, String sortProperty, String sortDirection, Filter filter) {
        this.page = page;
        this.size = size;
        this.sortProperty = sortProperty;
        this.sortDirection = sortDirection;
        this.filter = filter;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSortProperty() {
        return sortProperty;
    }

    public void setSortProperty(String sortProperty) {
        this.sortProperty = sortProperty;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
