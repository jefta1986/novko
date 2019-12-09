package com.novko.internal.categories;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

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

	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORIES_ID")
	private Set<Subcategory> subcategories = new HashSet<>();

	public Category() {
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
		return Collections.unmodifiableSet(subcategories);
	}

	public void setSubcategories(Set<Subcategory> subcategories) {
		this.subcategories = subcategories;
	}

	
//methods SubCategory	
	public Subcategory getSubcategoryByName(String subcategoryName) {
		for (Subcategory subcategory : this.subcategories) {
			if (subcategory.getName().equals(subcategoryName)) {
				return subcategory;
			}
		}

		throw new IllegalArgumentException("No such Subcategory with name '" + subcategoryName + "'");
	}

	public void addSubcategory(Subcategory subcategory) {

		if (subcategory == null)
			throw new IllegalArgumentException("Subcategory value is null");

		this.subcategories.add(subcategory);
	}

	public void deleteSubcategory(String subcategoryName) {
		this.subcategories.remove(getSubcategoryByName(subcategoryName));
	}


    public void updateSubcategory(String subcategoryName, String newName) {
		if(this.subcategories.contains(this.getSubcategoryByName(subcategoryName))){
			Subcategory subcategory = this.getSubcategoryByName(subcategoryName);
			subcategory.setName(newName);
		}

    }

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", subcategories=" + subcategories + "]";
	}

}
