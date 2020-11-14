package com.novko.api.response;

public class ImageResponse {

    private Long id;
    private String name;
    private String type;
    private byte[] data;
    private Boolean defaultPicture = Boolean.FALSE;

    public ImageResponse() {}

    public ImageResponse(Long id, String name, String type, byte[] data, Boolean defaultPicture) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.data = data;
        this.defaultPicture = defaultPicture;
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
