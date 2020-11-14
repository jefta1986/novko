package com.novko.api.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novko.internal.categories.Subcategory;
import com.novko.internal.products.Images;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRequest {

    private String name;
    private String code;
    private String brand;
    private String description;
    private Integer amountDin;
    private Integer amountEuro;
    private Integer quantity;

    public ProductRequest() {}

    public ProductRequest(String name, String code, String brand, String description, Integer amountDin, Integer amountEuro, Integer quantity) {
        this.name = name;
        this.code = code;
        this.brand = brand;
        this.description = description;
        this.amountDin = amountDin;
        this.amountEuro = amountEuro;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmountDin() {
        return amountDin;
    }

    public void setAmountDin(Integer amountDin) {
        this.amountDin = amountDin;
    }

    public Integer getAmountEuro() {
        return amountEuro;
    }

    public void setAmountEuro(Integer amountEuro) {
        this.amountEuro = amountEuro;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
