package com.novko.internal.dto;

import com.novko.internal.products.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SubcategoryDto implements Serializable{

	private static final long serialVersionUID = 6137549518895911202L;
	private String name;
//	private Set<Product> products = new HashSet<>();



	public SubcategoryDto() {}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "SubcategoryDto{" +
				"name='" + name +
				'}';
	}
}
