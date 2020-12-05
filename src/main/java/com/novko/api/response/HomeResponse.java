package com.novko.api.response;

import java.util.List;

public class HomeResponse {

    private List<CategoryResponse> categories;
    private List<ProductResponse> products;

    public HomeResponse() {}

    public HomeResponse(List<CategoryResponse> categories, List<ProductResponse> products) {
        this.categories = categories;
        this.products = products;
    }

    public List<CategoryResponse> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryResponse> categories) {
        this.categories = categories;
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }
}
