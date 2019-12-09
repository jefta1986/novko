package com.novko.api;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.novko.internal.categories.JpaCategoriesRepository;
import com.novko.internal.products.JpaProductsRepository;
import com.novko.internal.products.Product;

@RestController
@RequestMapping("/rest/products")
public class ProductsController {
	
	private JpaProductsRepository jpaProductsRepository;
	private JpaCategoriesRepository jpaCategoriesRepository;
	
	
	@Autowired
	public void setJpaProductsRepository(JpaProductsRepository jpaProductsRepository) {
		this.jpaProductsRepository = jpaProductsRepository;
	}

	
	@Autowired
	public void setJpaCategoriesRepository(JpaCategoriesRepository jpaCategoriesRepository) {
		this.jpaCategoriesRepository = jpaCategoriesRepository;
	}



	
	@GetMapping(value = "")
	public ResponseEntity<Set<Product>> getAllProducts() {
		return new ResponseEntity<Set<Product>>(jpaProductsRepository.getProducts(), HttpStatus.OK);
	}


	@GetMapping(value = "/getName")
	public ResponseEntity<Product> getProductByName(@RequestParam String productName) {
		return new ResponseEntity<Product>(jpaProductsRepository.getByName(productName), HttpStatus.OK);
	}

	@GetMapping(value = "/getCode")
	public ResponseEntity<Product> getProductByCode(@RequestParam String productCode) {
		return new ResponseEntity<Product>(jpaProductsRepository.getByCode(productCode), HttpStatus.OK);
	}


	@PostMapping(value = "/add")
	public ResponseEntity<String> addProductToSubcategory(@RequestParam String subcategoryName, @RequestParam String productName) {
		Product product = jpaProductsRepository.getByName(productName);
		jpaCategoriesRepository.addProductToSubcategory(subcategoryName, product);
		return new ResponseEntity<String>("product added to subcategory", HttpStatus.OK);
	}

	
	
	@PostMapping(value = "")
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		jpaProductsRepository.add(product);
		return new ResponseEntity<String>("product added", HttpStatus.OK);
	}


	@PutMapping(value = "")
	public ResponseEntity<String> updateProduct(@RequestBody Product product) {
		jpaProductsRepository.update(product);
		return new ResponseEntity<String>("product updated", HttpStatus.OK);
	}


	@DeleteMapping(value = "")
	public ResponseEntity<String> deleteProduct(@RequestParam String productName) {
		Product product = jpaProductsRepository.getByName(productName);
		if( product==null ){
			return new ResponseEntity<String>("product doesn't exists", HttpStatus.OK);
		}

		jpaProductsRepository.delete(product);
		return new ResponseEntity<String>("product deleted", HttpStatus.OK);
	}



}
