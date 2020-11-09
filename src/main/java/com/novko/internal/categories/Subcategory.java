package com.novko.internal.categories;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novko.internal.products.Product;

@Entity
@Table(name = "T_SUBCATEGORIES")
public class Subcategory implements Serializable{

	private static final long serialVersionUID = 6137549518895911202L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@SequenceGenerator(name = "seq_subcategories_gen", sequenceName = "seq_subcategories", allocationSize = 1, initialValue = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_subcategories_gen")
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	private Long id;


	@Column(name = "NAME", unique = true)
	private String name;

	@OneToMany(mappedBy = "subcategory", orphanRemoval = false, fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	@JsonIgnore
	private Set<Product> products = new HashSet<>();



	public Subcategory() {}

	public Subcategory(String name) {
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

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	
//add Product to Subcategory method	
	public void addProduct(Product product) {
		if (product == null){
			throw new IllegalArgumentException("product is null");
		}

		this.products.add(product);
		product.setSubcategory(this);
	}


	public Product getProductByName(String productName) {
		for (Product product : this.products) {
			if (product.getName().equals(productName)) {
				return product;
			}
		}

		throw new IllegalArgumentException("No such Product with name '" + productName + "'");
	}
	
//delete Product to Subcategory method		
	public void deleteProduct(Product product) {
		product.setSubcategory(null);
		this.products.remove(getProductByName(product.getName()));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Subcategory that = (Subcategory) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(name, that.name) &&
				Objects.equals(products, that.products);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, products);
	}
}
