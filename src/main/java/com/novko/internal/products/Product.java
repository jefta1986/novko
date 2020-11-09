package com.novko.internal.products;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novko.internal.categories.Subcategory;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


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
	
	@Column(columnDefinition = "TEXT", name = "DESCRIPTION")
//	@Lob
//	@Type(type = "org.hibernate.type.TextType")
	private String description;
	
	@Column(name = "AMOUNT_DIN")
	private Integer amountDin;
	
	@Column(name = "AMOUNT_EURO")
	private Integer amountEuro;
	
	@Column(name = "QUANTITY")
	private Integer quantity;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUBCATEGORIES_ID")
	private Subcategory subcategory;


//	@JsonIgnore
	@OneToMany(mappedBy = "product" ,cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<Images> images = new ArrayList<>();

	
//	@OneToMany(mappedBy = "product")
//	@JsonIgnore
////	@JsonBackReference
//	private List<Cart> carts = new ArrayList<>();


	@Column(name = "ENABLED")
	private boolean enabled;
	
	
	public Product() {}


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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
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

//	public List<Cart> getCarts() {
//		return carts;
//	}
//
//
//	public void setCarts(List<Cart> carts) {
//		this.carts = carts;


	public List<Images> getImages() {
		return images;
	}

	public void setImages(List<Images> images) {
		this.images = images;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Product product = (Product) o;
		return enabled == product.enabled &&
				Objects.equals(id, product.id) &&
				Objects.equals(name, product.name) &&
				Objects.equals(code, product.code) &&
				Objects.equals(description, product.description) &&
				Objects.equals(amountDin, product.amountDin) &&
				Objects.equals(amountEuro, product.amountEuro) &&
				Objects.equals(quantity, product.quantity) &&
				Objects.equals(subcategory, product.subcategory) &&
				Objects.equals(images, product.images);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, code, description, amountDin, amountEuro, quantity, subcategory, images, enabled);
	}

}
