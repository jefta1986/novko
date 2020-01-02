package com.novko.internal.categories;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

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
	
//persist, izmeni i za delete
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	@JoinColumn(name = "SUBCATEGORIES_ID")
	private Set<Product> products = new HashSet<>();
	
	

	public Subcategory() {}

	
	
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
		return Collections.unmodifiableSet(products);
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
		this.products.remove(getProductByName(product.getName()));
	}



	@Override
	public String toString() {
		return "Subcategory [id=" + id + ", name=" + name + ", products=" + products + "]";
	}



	
	

}
