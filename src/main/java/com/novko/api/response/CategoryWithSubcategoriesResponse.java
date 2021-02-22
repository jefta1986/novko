package com.novko.api.response;

import java.util.List;

public class CategoryWithSubcategoriesResponse {

    private Long id;
    private String name;
    private String nameSr;
    private List<SubcategoryResponse> subcategories;

    public CategoryWithSubcategoriesResponse() {}

    public CategoryWithSubcategoriesResponse(Long id, String name, String nameSr, List<SubcategoryResponse> subcategories) {
        this.id = id;
        this.name = name;
        this.nameSr = nameSr;
        this.subcategories = subcategories;
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

    public String getNameSr() {
        return nameSr;
    }

    public void setNameSr(String nameSr) {
        this.nameSr = nameSr;
    }

    public List<SubcategoryResponse> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<SubcategoryResponse> subcategories) {
        this.subcategories = subcategories;
    }
}
