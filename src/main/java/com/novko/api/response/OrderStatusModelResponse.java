package com.novko.api.response;

import java.util.List;

public class OrderStatusModelResponse {

    private Boolean status;
    private List<ProductResponse> invalidProducts;

    public OrderStatusModelResponse() {}

    public OrderStatusModelResponse(Boolean status, List<ProductResponse> invalidProducts) {
        this.status = status;
        this.invalidProducts = invalidProducts;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<ProductResponse> getInvalidProducts() {
        return invalidProducts;
    }

    public void setInvalidProducts(List<ProductResponse> invalidProducts) {
        this.invalidProducts = invalidProducts;
    }
}
