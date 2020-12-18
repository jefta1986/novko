package com.novko.api.request;

import com.novko.internal.products.Product;

import java.util.List;

public class OrderStatusModelRequest {

    private Boolean status;
    private List<Product> invalidProducts;

    public OrderStatusModelRequest() {}

    public OrderStatusModelRequest(Boolean status, List<Product> invalidProducts) {
        this.status = status;
        this.invalidProducts = invalidProducts;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Product> getInvalidProducts() {
        return invalidProducts;
    }

    public void setInvalidProducts(List<Product> invalidProducts) {
        this.invalidProducts = invalidProducts;
    }
}
