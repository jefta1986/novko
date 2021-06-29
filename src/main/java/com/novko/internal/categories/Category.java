package com.novko.internal.categories;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novko.api.exception.CustomIllegalArgumentException;
import com.novko.api.exception.CustomResourceNotFoundException;

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

    @Column(name = "NAME_SR", unique = true)
    private String nameSr;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORIES_ID")
//	@Fetch(FetchMode.SUBSELECT)
    private Set<Subcategory> subcategories = new HashSet<>();

    public Category() {
    }

    public Category(String name, String nameSr) {
        this.name = name;
        this.nameSr = nameSr;
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

    public String getNameSr() {
        return nameSr;
    }

    public void setNameSr(String nameSr) {
        this.nameSr = nameSr;
    }

    public Set<Subcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(Set<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }


    //methods SubCategory
    public Optional<Subcategory> getSubcategoryByName(String subcategoryName) {

        for (Subcategory subcategory : this.subcategories) {
            if (subcategory.getName().equals(subcategoryName)) {
                return Optional.of(subcategory);
            }
        }

        return Optional.ofNullable(null);
//		throw new CustomIllegalArgumentException("No such Subcategory with name: " + subcategoryName);
    }

//	public boolean isSubcategoryExists(String subcategoryName) {
//		for (Subcategory subcategory : this.subcategories) {
//			if (subcategory.getName().equals(subcategoryName)) {
//				return true;
//			}
//		}
//		return false;
//	}

    public Category addSubcategory(String subcategoryName, String subcategoryNameSr) {
        if (subcategoryName == null && subcategoryName.isEmpty() && subcategoryNameSr == null && subcategoryNameSr.isEmpty()) {
            throw new CustomIllegalArgumentException("Subcategory value is null");
        }

        Optional<Subcategory> optionalSubcategory = this.getSubcategoryByName(subcategoryName);
        if (optionalSubcategory.isPresent()) {
            throw new CustomIllegalArgumentException("Subcategory exists in category");
        } else {
            this.subcategories.add(new Subcategory(subcategoryName, subcategoryNameSr));
        }
        return this;
    }

    public void deleteSubcategory(String subcategoryName) {
        Optional<Subcategory> optionalSubcategory = this.getSubcategoryByName(subcategoryName);
        if (!optionalSubcategory.isPresent()) {
            throw new CustomResourceNotFoundException("Subcategory doesn't exist");
        }

        this.subcategories.remove(optionalSubcategory.get());
    }


    public Subcategory updateSubcategory(Long subcategoryId, String newName, String newNameSr) {
        Optional<Subcategory> optionalSubcategory = this.getSubcategoryById(subcategoryId);
        if (!optionalSubcategory.isPresent()) {
            throw new CustomResourceNotFoundException("Subcategory doesn't exist");
        }

        Subcategory subcategory = optionalSubcategory.get();
        if(newName != null && !newName.isEmpty() && !newName.equals(subcategory.getName())) {
                subcategory.setName(newName);
        }
        if(newNameSr != null && !newNameSr.isEmpty() && !newNameSr.equals(subcategory.getName())) {
            subcategory.setNameSr(newNameSr);
        }

        return subcategory;
    }

    private Optional<Subcategory> getSubcategoryById(Long id) {
        for (Subcategory subcategory : this.subcategories) {
            if (subcategory.getId().equals(id)) {
                return Optional.of(subcategory);
            }
        }

        return Optional.ofNullable(null);
    }

}
