package com.novko.api;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.novko.internal.categories.Category;
import com.novko.internal.categories.JpaCategoriesRepository;
import com.novko.internal.categories.Subcategory;

@RestController
@RequestMapping("/rest/categories")
public class CategoriesController {

	private JpaCategoriesRepository jpaCategoriesRepository;

	@Autowired
	public void setJpaCategoriesRepository(JpaCategoriesRepository jpaCategoriesRepository) {
		this.jpaCategoriesRepository = jpaCategoriesRepository;
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
	public ResponseEntity<Set<Category>> getCategories() {
		return new ResponseEntity<Set<Category>>(jpaCategoriesRepository.getAllCategories(), HttpStatus.OK);
	}

// Subcategory 	
	
	
	
	@GetMapping(value = "/getSubcategory")
	public ResponseEntity<Subcategory> getSubcategory(@RequestParam String name) {
		return new ResponseEntity<Subcategory>(jpaCategoriesRepository.getSubcategoryByName(name), HttpStatus.OK);
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
