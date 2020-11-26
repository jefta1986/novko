package com.novko.api.response;

public class CategoryResponse {

    private Long id;
    private String name;
    private String nameSr;

    public CategoryResponse() {}

    public CategoryResponse(Long id, String name, String nameSr) {
        this.id = id;
        this.name = name;
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

    public String getNameSr() {
        return nameSr;
    }

    public void setNameSr(String nameSr) {
        this.nameSr = nameSr;
    }

}
