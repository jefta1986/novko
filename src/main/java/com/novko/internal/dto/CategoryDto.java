package com.novko.internal.dto;

import com.novko.internal.categories.Subcategory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CategoryDto implements Serializable {

	private static final long serialVersionUID = -7429607374499798862L;

	private String name;
	private Set<SubcategoryDto> subcategories = new HashSet<>();


	public CategoryDto() {
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Set<SubcategoryDto> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(Set<SubcategoryDto> subcategories) {
		this.subcategories = subcategories;
	}

	@Override
	public String toString() {
		return "CategoryDto{" +
				"name='" + name + '\'' +
				", subcategories=" + subcategories +
				'}';
	}
}
