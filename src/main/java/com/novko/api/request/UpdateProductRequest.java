package com.novko.api.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateProductRequest {

    private Long id;
    private String name;
    private String nameSr;
    private String code;
    private String brand;
    private String description;
    private String descriptionSr;
    private Integer amountDin;
    private Integer amountEuro;
    private Integer quantity;
    private Boolean enabled;
    @NotNull
    @NotEmpty
    private String subcategoryName;

    public UpdateProductRequest() {}

    public String getNameSr() {
        return nameSr;
    }

    public void setNameSr(String nameSr) {
        this.nameSr = nameSr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescriptionSr() {
        return descriptionSr;
    }

    public void setDescriptionSr(String descriptionSr) {
        this.descriptionSr = descriptionSr;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }
}
