package com.novko.api;


import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.novko.internal.dto.ProductWithImagesDto;
import com.novko.internal.ehcache.CacheEventLogger;
import com.novko.internal.products.Images;
import com.novko.internal.products.JpaImagesRepository;
import org.apache.commons.io.IOUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

	private static final Logger LOG = LoggerFactory.getLogger(ProductsController.class);
	
	private JpaProductsRepository jpaProductsRepository;
	private JpaCategoriesRepository jpaCategoriesRepository;
	private JpaImagesRepository jpaImagesRepository;
	private ModelMapper modelMapper;
	
	
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

	@Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}



	@GetMapping(value = "/images")
	public ResponseEntity<List<ProductWithImagesDto>> getAllProductsWithImages() {
		Set<Product> products = jpaProductsRepository.getProductsWithImages();
		List<ProductWithImagesDto> productWithImages = products.stream().map(product -> modelMapper.map(product, ProductWithImagesDto.class)).collect(Collectors.toList());


		return new ResponseEntity<List<ProductWithImagesDto>>(productWithImages, HttpStatus.OK);
	}

	@GetMapping(value = "")
	public ResponseEntity<Set<Product>> getAllProductsWithoutImages() {
		return new ResponseEntity<Set<Product>>(jpaProductsRepository.getProducts(), HttpStatus.OK);
	}


    //Product with default image only
	@GetMapping(value = "/getName")
	public ResponseEntity<Product> getProductByName(@RequestParam String productName) {
		return new ResponseEntity<Product>(jpaProductsRepository.getByName(productName), HttpStatus.OK);
	}


	//Product with all Images
	@GetMapping(value = "/getName/images")
	public ResponseEntity<ProductWithImagesDto> getProductWithImages(@RequestParam String productName) {
		Product product = jpaProductsRepository.getProductWithImages(productName);
		ProductWithImagesDto productWithImagesDto = modelMapper.map(product, ProductWithImagesDto.class);

		LOG.info("********log  " + Product.ispisi(product));

		return new ResponseEntity<ProductWithImagesDto>(productWithImagesDto, HttpStatus.OK);


	}


	@GetMapping(value = "/getCode")
	@Cacheable(value = "product", key = "#productCode")
	public ResponseEntity<Product> getProductByCode(@RequestParam String productCode) {
		return new ResponseEntity<Product>(jpaProductsRepository.getByCode(productCode), HttpStatus.OK);
	}


	@PostMapping(value = "/add")
//	@CacheEvict(value = "") da li za product ili subcategory cache dodati ??
	public ResponseEntity<String> addProductToSubcategory(@RequestParam String subcategoryName, @RequestParam String productName) {
		Product product = jpaProductsRepository.getByName(productName);
		jpaCategoriesRepository.addProductToSubcategory(subcategoryName, product);
		return new ResponseEntity<String>("product added to subcategory", HttpStatus.OK);
	}

	
	
	@PostMapping(value = "")
//	@CacheEvict(value = "product", key = "#product.code")    da li po code ili name ??
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		jpaProductsRepository.add(product);
		return new ResponseEntity<String>("product added", HttpStatus.OK);
	}


	@PostMapping(value = "/savewithimage")
//	@CacheEvict(value = "productsWithImages", key = "")    po kom kljucu da ga evictujem ??
	public ResponseEntity<String> saveProductWithImages( @RequestParam(value = "product") String product, @RequestParam(value = "file") MultipartFile[] multipartFiles) throws  IOException {
		List<Images> images = new ArrayList<>();

		for (MultipartFile file : multipartFiles) {
			Images image = new Images();

			byte[] data = IOUtils.toByteArray(file.getInputStream());

//			byte[] data = new byte[0];
//			try {
//				data = file.getBytes();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

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


	@PostMapping(value = "/removeImage")
	public ResponseEntity<String> removeImage( @RequestParam(value = "imageId") Long imageId) {
		jpaImagesRepository.remove(imageId);
		return new ResponseEntity<String>("image removed", HttpStatus.OK);
	}


	@PutMapping(value = "/updateImage")
	public ResponseEntity<String> updateImage( @RequestParam(value = "imageId") Long imageId) {
		jpaImagesRepository.update(imageId);
		return new ResponseEntity<String>("image updated", HttpStatus.OK);
	}



	@PutMapping(value = "")
	//	@CacheEvict(value = "productsWithImages", key = "#product.code")
	public ResponseEntity<String> updateProduct(@RequestBody ProductWithImagesDto product) {
		ModelMapper modelMapper = new ModelMapper();
		Product productRequest = modelMapper.map(product, Product.class);
		Product productDb = jpaProductsRepository.getById(product.getId());
		if (productDb == null) return new ResponseEntity<String>("product doesn't exist", HttpStatus.OK);

		List<Images> imagesRequest = productRequest.getImages();
		if(imagesRequest!=null && imagesRequest.size()>0){

			for (Images image : imagesRequest) {
				productDb.getImages().add(image);
			}

		}


		productDb.setName(productRequest.getName());
		productDb.setQuantity(productRequest.getQuantity());
		productDb.setName(productRequest.getName());
		productDb.setAmountDin(productRequest.getAmountDin());
		productDb.setAmountEuro(productRequest.getAmountEuro());
		productDb.setDescription(productRequest.getDescription());
		productDb.setCode(productRequest.getCode());

//		else {
//			productRequest.getImages().addAll(productDb.getImages());
//			productRequest.setDefaultPicture(null);
//		}

		jpaProductsRepository.update(productDb);
		return new ResponseEntity<String>("product updated", HttpStatus.OK);
	}

//ne svidja mi se sto setuje listu slika uvek sa slikama iz db
//	@PutMapping(value = "")
//	public ResponseEntity<String> updateProduct(@RequestBody ProductWithImagesDto product) {
//		ModelMapper modelMapper = new ModelMapper();
//		Product productRequest = modelMapper.map(product, Product.class);
//		Product productDb = jpaProductsRepository.getById(product.getId());
//		if (productDb == null) return new ResponseEntity<String>("product doesn't exist", HttpStatus.OK);
//
//		List<Images> imagesRequest = productRequest.getImages();
//		if(productRequest.getImages()!=null && productRequest.getImages().size()>0){
//			List<Images> imagesFromDb = productDb.getImages();
//			List<Images> imagesFromRequest = productRequest.getImages();
//			List<Images> newImages = new ArrayList<>();
//			for (Images imageDb : imagesFromDb) {
//				for (Images imageRequest : imagesFromRequest) {
//					if (!imageRequest.equals(imageDb) && !newImages.contains(imageRequest) && !imagesFromDb.contains(imageRequest)) {
//						newImages.add(imageRequest);
//					}
//				}
//			}
//			productRequest.getImages().clear();
//
//			if(newImages!=null && newImages.size()>0) {
//				productRequest.getImages().addAll(newImages);
//			}
//			productRequest.getImages().addAll(imagesFromDb);
//			productRequest.setDefaultPicture(null);
//		}
//		else {
//			productRequest.getImages().addAll(productDb.getImages());
//			productRequest.setDefaultPicture(null);
//		}
//
//		jpaProductsRepository.update(productRequest);
//		return new ResponseEntity<String>("product updated", HttpStatus.OK);
//	}


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
