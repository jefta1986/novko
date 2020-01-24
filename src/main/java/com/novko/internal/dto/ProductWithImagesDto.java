package com.novko.internal.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novko.internal.cart.Cart;
import com.novko.internal.products.Images;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductWithImagesDto implements Serializable {

	private static final long serialVersionUID = 3513791975400519449L;

	private Long id;
	private String name;
	private String code;
	private String description;
	private Integer amountDin;
	private Integer amountEuro;
	private Integer quantity;
	private byte[] defaultPicture;
	private List<Images> images = new ArrayList<>();


	public ProductWithImagesDto() {}


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
		return "ProductWithImagesDto{" +
				"id=" + id +
				", name='" + name + '\'' +
				", code='" + code + '\'' +
				", description='" + description + '\'' +
				", amountDin=" + amountDin +
				", amountEuro=" + amountEuro +
				", quantity=" + quantity +
				", defaultPicture=" + Arrays.toString(defaultPicture) +
				", images=" + images +
				'}';
	}
}
