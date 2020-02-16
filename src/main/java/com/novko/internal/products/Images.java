package com.novko.internal.products;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "T_IMAGES")
public class Images implements Serializable {

    private static final long serialVersionUID = 8277261869164320764L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	@SequenceGenerator(name = "seq_products_gen", sequenceName = "seq_products", allocationSize = 1, initialValue = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_products_gen")
    @Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;


    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "DATA")
//    @Basic(fetch = FetchType.LAZY)
    private byte[] data;


    @Column(name = "DEFAULT_PICTURE")
    private Long defaultPicture;


    public Images() {}

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


    public Long getDefaultPicture() {
        return defaultPicture;
    }

    public void setDefaultPicture(Long defaultPicture) {
        this.defaultPicture = defaultPicture;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Images images = (Images) o;
        return  Objects.equals(name, images.name) &&
                Objects.equals(type, images.type) &&
                Arrays.equals(data, images.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, type);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }


    @Override
    public String toString() {
        return "Images{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", data=" + Arrays.toString(data) +
                ", defaultPicture=" + defaultPicture +
                '}';
    }
}
