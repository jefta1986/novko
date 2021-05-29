package com.novko.api.request;

import java.time.OffsetDateTime;

public class OrderFilter implements Filter {

    private Boolean status;
    private String userPart;
    private OffsetDateTime fromDate;
    private OffsetDateTime toDate;

    public OrderFilter() {}

    public OrderFilter(Boolean status, String userPart, OffsetDateTime fromDate, OffsetDateTime toDate) {
        this.status = status;
        this.userPart = userPart;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUserPart() {
        return userPart;
    }

    public void setUserPart(String userPart) {
        this.userPart = userPart;
    }

    public OffsetDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(OffsetDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public OffsetDateTime getToDate() {
        return toDate;
    }

    public void setToDate(OffsetDateTime toDate) {
        this.toDate = toDate;
    }

}
