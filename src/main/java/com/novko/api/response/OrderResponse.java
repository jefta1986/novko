package com.novko.api.response;

import java.time.OffsetDateTime;
import java.util.List;

public class OrderResponse {

    private Long id;
    private OffsetDateTime orderDate;
    private Integer totalAmountDin;
    private Integer totalAmountEuro;
    private Integer quantity;
    private Boolean status;
    private List<CartResponse> carts;
    private UserResponse user;
    private String name;
    private String surname;
    private String phoneNumber;
    private String country;
    private String city;
    private String address;
    private String postalCode;
    private String description;

    public OrderResponse() {}

    public OrderResponse(Long id, OffsetDateTime orderDate, Integer totalAmountDin, Integer totalAmountEuro, Integer quantity, Boolean status, List<CartResponse> carts, UserResponse user, String name, String surname, String phoneNumber, String country, String city, String address, String postalCode, String description) {
        this.id = id;
        this.orderDate = orderDate;
        this.totalAmountDin = totalAmountDin;
        this.totalAmountEuro = totalAmountEuro;
        this.quantity = quantity;
        this.status = status;
        this.carts = carts;
        this.user = user;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.city = city;
        this.address = address;
        this.postalCode = postalCode;
        this.description = description;
    }

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

    public List<CartResponse> getCarts() {
        return carts;
    }

    public void setCarts(List<CartResponse> carts) {
        this.carts = carts;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
