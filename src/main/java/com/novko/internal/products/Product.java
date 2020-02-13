package com.novko.internal.products;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novko.internal.cart.Cart;

import org.apache.commons.io.IOUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;


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
	
	@Column(name = "DESCRIPTION")
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String description;
	
	@Column(name = "AMOUNT_DIN")
	private Integer amountDin;
	
	@Column(name = "AMOUNT_EURO")
	private Integer amountEuro;
	
	@Column(name = "QUANTITY")
	private Integer quantity;


	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	@Column(name = "DEFAULT_PICTURE")
//	@Basic(fetch = FetchType.LAZY)
	private byte[] defaultPicture;


	@JsonIgnore
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID")
//	@Fetch(FetchMode.SUBSELECT)
	@Fetch(FetchMode.JOIN)
	private List<Images> images = new ArrayList<>();

	
	@OneToMany(mappedBy = "product")
	@JsonIgnore
//	@JsonBackReference
	private List<Cart> carts = new ArrayList<>();
	
	
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


	public List<Cart> getCarts() {
		return carts;
	}


	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}


	public byte[] getDefaultPicture() {
		return defaultPicture;
	}

	public void setDefaultPicture(byte[] defaultPicture) {
		this.defaultPicture = defaultPicture;
	}

	public List<Images> getImages() {
		return images;
	}

	public void setImages(List<Images> images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", code=" + code + ", description=" + description
				+ ", amountDin=" + amountDin + ", amountEuro=" + amountEuro + ", quantity=" + quantity + "]";
	}

	public static String ispisi(Product product){
		StringBuilder sb = new StringBuilder();

		sb.append(product).append(": ");

		for (Images image: product.getImages()) {
			sb.append(image.getId()).append(image.getName()).append("\n");
		}
		return sb.toString();
	}


	

	



	
	
	
	
	
	
	

}
