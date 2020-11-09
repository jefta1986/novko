package com.novko.api;

import java.util.List;
import java.util.Optional;

import com.novko.api.mapper.CategoryMapper;
import com.novko.api.mapper.SubcategoryMapper;
import com.novko.api.request.CategoryRequest;
import com.novko.api.request.SubcategoryRequest;
import com.novko.api.response.CategoryResponse;
import com.novko.api.response.SubcategoryResponse;
import com.novko.internal.categories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/categories")
public class CategoriesController {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoriesController(CategoryService categoryService, CategoryRepository categoryRepository, SubcategoryRepository subcategoryRepository, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
        this.subcategoryRepository = subcategoryRepository;
        this.modelMapper = modelMapper;
    }

	@PostMapping(value = "")
    //	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> saveCategory(@RequestParam String name) {
        if(!categoryRepository.existsCategoryByName(name)) {
            categoryRepository.save(new Category(name));
            return new ResponseEntity<String>("Category saved successfully!", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("Category already exists!", HttpStatus.OK);
	}

    @PutMapping(value = "")
    public ResponseEntity<String> updateCategory(@RequestBody CategoryRequest categoryRequest) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryRequest.getId());
        if(!optionalCategory.isPresent()) {
            return new ResponseEntity<String>("Category doesn't exists!", HttpStatus.NO_CONTENT);
        }

        Category category = optionalCategory.get();
        if(!category.getName().equals(categoryRequest.getName())) {
            category.setName(categoryRequest.getName());
            categoryRepository.save(category);
            return new ResponseEntity<String>("Category updated!", HttpStatus.OK);
        }

        return new ResponseEntity<String>("You can't update same objects!", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@RequestParam String categoryName) {
        categoryService.deleteByName(categoryName);
    }

    @GetMapping(value = "/{categoryName}")
    public CategoryResponse getCategoryByName(@PathVariable String categoryName) {
        return CategoryMapper.INSTANCE.toDto(categoryRepository.findByName(categoryName));
    }

    @GetMapping(value = "")
    public List<CategoryResponse> getCategories() {
        return CategoryMapper.INSTANCE.listToDto(categoryRepository.findAll());
    }

// Subcategory methods

    @GetMapping(value = "/subcategories")
    public List<SubcategoryResponse> getSubcategoriesInCategory(@RequestParam String categoryName) {
        return SubcategoryMapper.INSTANCE.listToDto(categoryRepository.findSubcategories(categoryName));
    }


    @PostMapping(value = "/subcategories")
//	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addSubcategory(@RequestParam String subcategoryName,
                                                 @RequestParam String categoryName) {
        categoryService.addSubcategory(subcategoryName, categoryName);
        return new ResponseEntity<String>("Subcategory added", HttpStatus.OK);
    }


    @DeleteMapping(value = "/subcategories")
    public ResponseEntity<String> deleteSubcategory(@RequestParam String categoryName, @RequestParam String subcategoryName) {
        categoryService.deleteSubcategory(categoryName, subcategoryName);
        return new ResponseEntity<String>("Subcategory deleted", HttpStatus.OK);
    }


    @PutMapping(value = "/subcategories")
    @ResponseStatus(HttpStatus.OK)
    public void updateSubcategory(@RequestBody SubcategoryRequest subcategoryRequest, @RequestParam String subcategoryName, @RequestParam String categoryName) {
        if (categoryRepository.findByName(categoryName) != null && subcategoryRepository.findById(subcategoryRequest.getId()).get() != null) {
            categoryService.updateSubcategory(categoryName, subcategoryRequest.getName(), subcategoryName);
        }
    }

}
