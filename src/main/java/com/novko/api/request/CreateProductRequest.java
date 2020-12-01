package com.novko.api.request;

public class CreateProductRequest {

    private String name;
    private String code;
    private String brand;
    private String description;
    private String descriptionSr;
    private Integer amountDin;
    private Integer amountEuro;
    private Integer quantity;
    private String subcategoryName;

    public CreateProductRequest() {}

    public CreateProductRequest(String name, String code, String brand, String description, String descriptionSr, Integer amountDin, Integer amountEuro, Integer quantity, String subcategoryName) {
        this.name = name;
        this.code = code;
        this.brand = brand;
        this.description = description;
        this.descriptionSr = descriptionSr;
        this.amountDin = amountDin;
        this.amountEuro = amountEuro;
        this.quantity = quantity;
        this.subcategoryName = subcategoryName;
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

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }
}
