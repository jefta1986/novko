package com.novko.api;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novko.internal.products.Images;
import com.novko.internal.products.JpaImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.novko.internal.categories.JpaCategoriesRepository;
import com.novko.internal.products.JpaProductsRepository;
import com.novko.internal.products.Product;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/rest/products")
public class ProductsController {
	
	private JpaProductsRepository jpaProductsRepository;
	private JpaCategoriesRepository jpaCategoriesRepository;
	private JpaImagesRepository jpaImagesRepository;
	
	
	@Autowired
	public void setJpaProductsRepository(JpaProductsRepository jpaProductsRepository) {
		this.jpaProductsRepository = jpaProductsRepository;
	}

	
	@Autowired
	public void setJpaCategoriesRepository(JpaCategoriesRepository jpaCategoriesRepository) {
		this.jpaCategoriesRepository = jpaCategoriesRepository;
	}


	@Autowired
    public void setJpaImagesRepository(JpaImagesRepository jpaImagesRepository) {
        this.jpaImagesRepository = jpaImagesRepository;
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


	@PostMapping(value = "/savewithimage")
	public ResponseEntity<String> saveProductWithImages( @RequestParam(value = "product") String product, @RequestParam(value = "file") MultipartFile[] multipartFiles) throws  IOException {
		List<Images> images = new ArrayList<>();
		for (MultipartFile file : multipartFiles) {
			Images image = new Images();

			byte[] data = new byte[0];
			try {
				data = file.getBytes();
			} catch (IOException e) {
				e.printStackTrace();
			}

			image.setName(file.getOriginalFilename());
			image.setType(file.getContentType());
			image.setData(data);

			images.add(image);
		}

		ObjectMapper objectMapper = new ObjectMapper();
		Product productDeserial = objectMapper.readValue(product, Product.class);
		productDeserial.setImages(images);
		jpaProductsRepository.add(productDeserial);
		return new ResponseEntity<String>("product with images added", HttpStatus.OK);
	}



    @PostMapping(value = "/setdefault")
    public ResponseEntity<String> setDefaultImage( @RequestParam(value = "productId") Long productId,  @RequestParam(value = "imageId") Long imageId) {

        jpaImagesRepository.setDefault(productId, imageId);

        Product product = jpaProductsRepository.getById(productId);
        Images image = jpaImagesRepository.getById(imageId);
        product.setDefaultPicture(image.getData());
        jpaProductsRepository.update(product);
        return new ResponseEntity<String>("set default picture", HttpStatus.OK);
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
