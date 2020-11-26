package com.novko.api.request;

import java.time.OffsetDateTime;
import java.util.List;

public class OrderRequest {

    private Long id;
    private OffsetDateTime orderDate;
    private Integer totalAmountDin;
    private Integer totalAmountEuro;
    private Integer quantity;
    private Boolean status;
    private List<CartRequest> carts;
    private UserRequest user;
//    private String name;
//    private String surname;
//    private String phoneNumber;
//    private String country;
//    private String city;
//    private String address;
//    private String postalCode;
//    private String description;

    public OrderRequest() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(OffsetDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getTotalAmountDin() {
        return totalAmountDin;
    }

    public void setTotalAmountDin(Integer totalAmountDin) {
        this.totalAmountDin = totalAmountDin;
    }

    public Integer getTotalAmountEuro() {
        return totalAmountEuro;
    }

    public void setTotalAmountEuro(Integer totalAmountEuro) {
        this.totalAmountEuro = totalAmountEuro;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<CartRequest> getCarts() {
        return carts;
    }

    public void setCarts(List<CartRequest> carts) {
        this.carts = carts;
    }

    public UserRequest getUser() {
        return user;
    }

    public void setUser(UserRequest user) {
        this.user = user;
    }
}
