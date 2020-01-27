package com.novko.api;

import java.util.Set;
import java.util.stream.Collectors;

import com.novko.internal.dto.SubcategoryDto;
import com.novko.internal.dto.SubcategoryWithProductsDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.novko.internal.categories.Category;
import com.novko.internal.categories.JpaCategoriesRepository;
import com.novko.internal.categories.Subcategory;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/rest/categories")
public class CategoriesController {

	private JpaCategoriesRepository jpaCategoriesRepository;
	private ModelMapper modelMapper;

	@Autowired
	public void setJpaCategoriesRepository(JpaCategoriesRepository jpaCategoriesRepository) {
		this.jpaCategoriesRepository = jpaCategoriesRepository;
	}

	@Autowired
	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@PostMapping(value = "")
	public ResponseEntity<String> saveCategory(@RequestBody Category category) {
		jpaCategoriesRepository.add(category);
		return new ResponseEntity<String>("category added", HttpStatus.OK);
	}

	@PutMapping(value = "")
	public ResponseEntity<String> updateCategory(@RequestBody Category category) {
		jpaCategoriesRepository.update(category);
		return new ResponseEntity<String>("category updated", HttpStatus.OK);
	}

	@DeleteMapping(value = "")
	public ResponseEntity<String> deleteCategory(@RequestParam String categoryName) {
		jpaCategoriesRepository.delete(categoryName);
		return new ResponseEntity<String>("category deleted", HttpStatus.OK);
	}

	@GetMapping(value = "/{categoryName}")
	public ResponseEntity<Category> getCategoryByName(@PathVariable String categoryName) {
		return new ResponseEntity<Category>(jpaCategoriesRepository.getCategoryByName(categoryName), HttpStatus.OK);
	}

	@GetMapping(value = "")
	@RolesAllowed({"ROLE_USER", "ROLE_ADMIN'"})
	public ResponseEntity<Set<Category>> getCategories() {
		return new ResponseEntity<Set<Category>>(jpaCategoriesRepository.getAllCategories(), HttpStatus.OK);
	}

// Subcategory 	
	
	
	//Subcategory with all Products with default images(not all images)
	@GetMapping(value = "/getSubcategory")
	public ResponseEntity<SubcategoryWithProductsDto> getSubcategory(@RequestParam String name) {
		Subcategory subcategory = jpaCategoriesRepository.getSubcategoryByName(name);
		SubcategoryWithProductsDto subcategoryWithProductsDto = modelMapper.map(subcategory, SubcategoryWithProductsDto.class);

		return new ResponseEntity<SubcategoryWithProductsDto>(subcategoryWithProductsDto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSubcategories")
	@RolesAllowed({"ROLE_USER", "ROLE_ADMIN'"})
	public ResponseEntity<Set<Subcategory>> getSubcategories() {
		return new ResponseEntity<Set<Subcategory>>(jpaCategoriesRepository.getAllSubcategories(), HttpStatus.OK);
	}


	@GetMapping(value = "/getSubcategoriesWithProducts")
	@RolesAllowed({"ROLE_USER", "ROLE_ADMIN'"})
	public ResponseEntity<Set<SubcategoryWithProductsDto>> getSubcategoriesWithProducts() {

		Set<Subcategory> subcategories = jpaCategoriesRepository.getAllSubcategoriesWithProducts();

		Set<SubcategoryWithProductsDto> subcategoryWithProducts = subcategories.stream().map(subcategory -> modelMapper.map(subcategory, SubcategoryWithProductsDto.class)).collect(Collectors.toSet());

		return new ResponseEntity<Set<SubcategoryWithProductsDto>>(subcategoryWithProducts, HttpStatus.OK);
	}

	

	@PostMapping(value = "/addSubcategory")
	public ResponseEntity<String> addSubcategory(@RequestBody Subcategory subcategory,
			@RequestParam String categoryName) {
		jpaCategoriesRepository.addSubcategory(subcategory, categoryName);
		return new ResponseEntity<String>("subcategory added", HttpStatus.OK);
	}
	
	
	@DeleteMapping(value = "/deleteSubcategory")
	public ResponseEntity<String> deleteSubcategory(@RequestParam String categoryName, @RequestParam String subcategoryName) {
		jpaCategoriesRepository.deleteSubcategory(categoryName, subcategoryName);
		return new ResponseEntity<String>("subcategory deleted", HttpStatus.OK);
	}

	//update Subcategory object only!!!
	@PutMapping(value = "/updateSubcategory")
	public ResponseEntity<String> updateSubcategory(@RequestBody Subcategory subcategory, @RequestParam String subcategoryName, @RequestParam String categoryName) {
//		Category categoryDb = jpaCategoriesRepository.getCategoryByName(categoryName);

		if (jpaCategoriesRepository.getCategoryByName(categoryName)!=null && jpaCategoriesRepository.getSubcategoryByName(subcategory.getName())!=null){
			jpaCategoriesRepository.updateSubcategory(categoryName, subcategory.getName(), subcategoryName);
			return new ResponseEntity<String>("subcategory updated", HttpStatus.OK);
		}
		return new ResponseEntity<String>("error", HttpStatus.OK);
	}


	

}
