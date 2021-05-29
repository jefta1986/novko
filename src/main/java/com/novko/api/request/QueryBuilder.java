package com.novko.api.request;

public class QueryBuilder {
    private Integer page;
    private Integer size;
    private String sortProperty;
    private String sortDirection;
    private Filter filter;

    public QueryBuilder setPage(Integer page) {
        this.page = page;
        return this;
    }

    public QueryBuilder setSize(Integer size) {
        this.size = size;
        return this;
    }

    public QueryBuilder setSortProperty(String sortProperty) {
        this.sortProperty = sortProperty;
        return this;
    }

    public QueryBuilder setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
        return this;
    }

    public QueryBuilder setFilter(Filter filter) {
        this.filter = filter;
        return this;
    }

    public Query createQuery() {
        return new Query(page, size, sortProperty, sortDirection, filter);
    }
}