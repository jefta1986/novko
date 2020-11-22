package com.novko.api.response;

public class CartResponse {

    private Long id;
    private Integer quantity;
    private ProductResponse product;
    private Integer amountDin;
    private Integer amountEuro;

    public CartResponse() {}

    public CartResponse(Long id, Integer quantity, ProductResponse product, Integer amountDin, Integer amountEuro) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
        this.amountDin = amountDin;
        this.amountEuro = amountEuro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductResponse getProduct() {
        return product;
    }

    public void setProduct(ProductResponse product) {
        this.product = product;
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

}
