package com.novko.internal.dto;


import java.io.Serializable;

public class ImagesDto implements Serializable {

    private static final long serialVersionUID = 7158938578255846502L;

    private Long id;
    private String name;
    private String type;
    private byte[] data;
    private Boolean defaultPicture;

    public ImagesDto() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Boolean getDefaultPicture() {
        return defaultPicture;
    }

    public void setDefaultPicture(Boolean defaultPicture) {
        this.defaultPicture = defaultPicture;
    }
}
