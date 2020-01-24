package com.novko.internal.dto;

import com.novko.internal.products.Product;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class SubcategoryWithProductsDto implements Serializable{

	private static final long serialVersionUID = 1897275941996122806L;

	private Long id;
	private String name;
	private Set<Product> products = new HashSet<>();



	public SubcategoryWithProductsDto() {}


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

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "SubcategoryWithProductsDto{" +
				"name='" + name + '\'' +
				", products=" + products +
				'}';
	}
}
