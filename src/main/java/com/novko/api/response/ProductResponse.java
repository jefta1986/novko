package com.novko.api.response;


public class ProductResponse {

    private Long id;
    private String name;
    private String code;
    private String brand;
    private String description;
    private String descriptionSr;
    private Integer amountDin;
    private Integer amountEuro;
    private Integer quantity;
    private boolean enabled;
//    private ImageResponse image;

    public ProductResponse() {}

    public ProductResponse(Long id, String name, String code, String brand, String description, String descriptionSr, Integer amountDin, Integer amountEuro, Integer quantity, boolean enabled) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.brand = brand;
        this.description = description;
        this.descriptionSr = descriptionSr;
        this.amountDin = amountDin;
        this.amountEuro = amountEuro;
        this.quantity = quantity;
        this.enabled = enabled;
//        this.image = image;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

//    public ImageResponse getImage() {
//        return image;
//    }
//
//    public void setImage(ImageResponse image) {
//        this.image = image;
//    }
}
