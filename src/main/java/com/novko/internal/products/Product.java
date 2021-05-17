package com.novko.internal.products;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.novko.internal.categories.Subcategory;


@Entity
@Table(name = "T_PRODUCTS")
public class Product implements Serializable {

    private static final long serialVersionUID = 8523221699244811502L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	@SequenceGenerator(name = "seq_products_gen", sequenceName = "seq_products", allocationSize = 1, initialValue = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_products_gen")
    @Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
    private Long id;

    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "CODE", unique = true)
    private String code;

    @Column(name = "BRAND")
    private String brand;

    @Column(columnDefinition = "TEXT", name = "DESCRIPTION")
//	@Lob
//	@Type(type = "org.hibernate.type.TextType")
    private String description;

    @Column(columnDefinition = "TEXT", name = "DESCRIPTION_SR")
//	@Lob
//	@Type(type = "org.hibernate.type.TextType")
    private String descriptionSr;

    @Column(name = "AMOUNT_DIN")
    private Integer amountDin;

    @Column(name = "AMOUNT_EURO")
    private Integer amountEuro;

    @Column(name = "QUANTITY")
    private Integer quantity;

//    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUBCATEGORIES_ID")
    private Subcategory subcategory;

    @Column(name = "ENABLED")
    private Boolean enabled;

    //	@ElementCollection(fetch = FetchType.EAGER)
//	@CollectionTable(name = "T_IMAGES", joinColumns = @JoinColumn(name = "id"))
//	@Column(name="image")
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//    @JoinColumn(name = "PRODUCT_ID")
//    @Fetch(FetchMode.SUBSELECT)

//    @Column(name="images")
//    @ElementCollection(targetClass=String.class)

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "T_IMAGES", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "image")
    private List<String> images = new ArrayList<>();


//    private void addImage(List<String> imageList) {
//        this.images.addAll(imageList);
//        imageList.
//
//    }

//	@JsonIgnore
//	@OneToMany(mappedBy = "product" ,cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//	@Fetch(FetchMode.SUBSELECT)
//	private List<Images> images = new ArrayList<>();


//	@OneToMany(mappedBy = "product")
//	@JsonIgnore
////	@JsonBackReference
//	private List<Cart> carts = new ArrayList<>();


    public Product() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionSr() {
        return descriptionSr;
    }

    public void setDescriptionSr(String descriptionSr) {
        this.descriptionSr = descriptionSr;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
