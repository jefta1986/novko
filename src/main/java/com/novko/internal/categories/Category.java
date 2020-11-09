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
	public Subcategory getSubcategoryByName(String subcategoryName) {
		for (Subcategory subcategory : this.subcategories) {
			if (subcategory.getName().equals(subcategoryName)) {
				return subcategory;
			}
		}

		throw new IllegalArgumentException("No such Subcategory with name: " + subcategoryName);
	}

	public void addSubcategory(Subcategory subcategory) {
		if (subcategory == null) {
			throw new IllegalArgumentException("Subcategory value is null");
		}
		if (this.subcategories.contains(subcategory)) {
			throw new IllegalArgumentException("Subcategory exists");
		}

		this.subcategories.add(subcategory);
	}

	public void deleteSubcategory(String subcategoryName) {
		Subcategory subcategory = this.getSubcategoryByName(subcategoryName);
		if(subcategory != null)
			this.subcategories.remove(subcategory);
	}


    public void updateSubcategory(String subcategoryName, String newName) {
		Subcategory subcategory = this.getSubcategoryByName(subcategoryName);
		if(subcategory != null)
			subcategory.setName(newName);

    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Category category = (Category) o;
		return Objects.equals(id, category.id) &&
				Objects.equals(name, category.name) &&
				Objects.equals(subcategories, category.subcategories);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, subcategories);
	}

}
