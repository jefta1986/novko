package com.novko.internal.products;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

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
    @Column(name = "DATA")
//    @Basic(fetch = FetchType.LAZY)
    private byte[] data;


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

    @Override
    public String toString() {
        return "Images{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
