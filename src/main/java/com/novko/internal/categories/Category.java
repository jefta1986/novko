package com.novko.internal.categories;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_CATEGORIES")
public class Category implements Serializable {

	private static final long serialVersionUID = -7429607374499798862L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@SequenceGenerator(name = "seq_categories_gen", sequenceName = "seq_categories", allocationSize = 1, initialValue = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_categories_gen")
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	private Long id;

	@Column(name = "NAME", unique = true)
	private String name;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORIES_ID")
//	@Fetch(FetchMode.SUBSELECT)
	private Set<Subcategory> subcategories = new HashSet<>();

	public Category() {
	}

	public Category(String name) {
		this.name = name;
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

	public Set<Subcategory> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(Set<Subcategory> subcategories) {
		this.subcategories = subcategories;
	}

	
//methods SubCategory	
	public Subcategory getSubcategoryByName(String subcategoryName) throws IllegalArgumentException {
		for (Subcategory subcategory : this.subcategories) {
			if (subcategory.getName().equals(subcategoryName)) {
				return subcategory;
			}
		}

		throw new IllegalArgumentException("No such Subcategory with name: " + subcategoryName);
	}

	public Category addSubcategory(String subcategoryName) {
		if (subcategoryName == null && subcategoryName.isEmpty()) {
			throw new IllegalArgumentException("Subcategory value is null");
		}

		Subcategory subcategory = null;
		try {
			subcategory = this.getSubcategoryByName(subcategoryName);
		}catch (IllegalArgumentException e) {
			this.subcategories.add(new Subcategory(subcategoryName));
		}

		if(subcategory != null) {
			throw new IllegalArgumentException("Subcategory exists in category");
		}
		return this;
	}

	public void deleteSubcategory(String subcategoryName) {
		Subcategory subcategory = this.getSubcategoryByName(subcategoryName);
		if(subcategory != null) {
			this.subcategories.remove(subcategory);
		}
	}


    public Subcategory updateSubcategory(String subcategoryName, String newName) {
		Subcategory subcategory = this.getSubcategoryByName(subcategoryName);
//		if(subcategory == null || subcategory.getName().equals(newName)) {
//			return;
//		}
		subcategory.setName(newName);
		return subcategory;
    }

}
