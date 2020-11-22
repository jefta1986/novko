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
    private String name;
    private String surname;
    private String phoneNumber;
    private String country;
    private String city;
    private String address;
    private String postalCode;
    private String description;

    public OrderRequest() {}



}
