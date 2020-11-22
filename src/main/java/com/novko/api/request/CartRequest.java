package com.novko.api.request;

public class CartRequest {

    private Long id;
    private Integer quantity;
    private OrderRequest order;
    private ProductRequest product;
    private Integer amountDin;
    private Integer amountEuro;

    public CartRequest() {}

    public CartRequest(Long id, Integer quantity, OrderRequest order, ProductRequest product, Integer amountDin, Integer amountEuro) {
        this.id = id;
        this.quantity = quantity;
        this.order = order;
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

    public OrderRequest getOrder() {
        return order;
    }

    public void setOrder(OrderRequest order) {
        this.order = order;
    }

    public ProductRequest getProduct() {
        return product;
    }

    public void setProduct(ProductRequest product) {
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
