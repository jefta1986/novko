package com.novko.api;

import java.util.List;

import com.novko.api.mapper.CategoryMapper;
import com.novko.api.mapper.ProductMapper;
import com.novko.api.mapper.SubcategoryMapper;
import com.novko.api.request.CategoryRequest;
import com.novko.api.request.SubcategoryRequest;
import com.novko.api.response.CategoryResponse;
import com.novko.api.response.ProductResponse;
import com.novko.api.response.SubcategoryResponse;
import com.novko.internal.categories.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/categories")
public class CategoriesController {

    private final CategoryService categoryService;

    @Autowired
    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping(value = "")
    @ApiOperation(value = "Save Category")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponse saveCategory(@RequestParam String name) {
        return CategoryMapper.INSTANCE.toDto(categoryService.saveCategory(name));
    }

    @PatchMapping(value = "")
    @ApiOperation(value = "Update Category")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponse updateCategory(@RequestBody CategoryRequest categoryRequest) {
        return CategoryMapper.INSTANCE.toDto(categoryService.updateCategory(categoryRequest.getId(), categoryRequest.getName()));
    }

    @DeleteMapping(value = "")
    @ApiOperation(value = "Delete Category")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategory(@RequestParam String categoryName) {
        categoryService.deleteByName(categoryName);
    }

    @GetMapping(value = "/name")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
    public CategoryResponse getCategoryByName(@RequestParam String categoryName) {
        return CategoryMapper.INSTANCE.toDto(categoryService.findByName(categoryName));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get Category by Id")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
    public CategoryResponse getCategoryById(@PathVariable("id") Long id) {
        return CategoryMapper.INSTANCE.toDto(categoryService.findById(id));
    }

    @GetMapping(value = "")
    @ApiOperation(value = "Get All Categories")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
    public List<CategoryResponse> getCategories() {
        return CategoryMapper.INSTANCE.listToDto(categoryService.findAll());
    }


// Subcategory methods

    @GetMapping(value = "/subcategories")
    @ApiOperation(value = "Get All Subcategories in Category (by category name)")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
    public List<SubcategoryResponse> getSubcategoriesInCategory(@RequestParam String categoryName) {
        return SubcategoryMapper.INSTANCE.listToDto(categoryService.findSubcategories(categoryName));
    }


    @PostMapping(value = "/subcategories")
    @ApiOperation(value = "Add Subcategory in Category")
    @ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN')")
    public CategoryResponse addSubcategory(@RequestParam String subcategoryName, @RequestParam String categoryName) {
        return CategoryMapper.INSTANCE.toDto(categoryService.addSubcategory(subcategoryName, categoryName));
    }


    @DeleteMapping(value = "/subcategories")
    @ApiOperation(value = "Delete Subcategory")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSubcategory(@RequestParam String categoryName, @RequestParam String subcategoryName) {
        categoryService.deleteSubcategory(categoryName, subcategoryName);
    }


    @PatchMapping(value = "/subcategories")
    @ApiOperation(value = "Update Subcategory")
    @PreAuthorize("hasRole('ADMIN')")
    public SubcategoryResponse updateSubcategory(@RequestBody SubcategoryRequest subcategoryRequest, @RequestParam String subcategoryName, @RequestParam String categoryName) {
        return SubcategoryMapper.INSTANCE.toDto(categoryService.updateSubcategory(categoryName, subcategoryRequest.getId(), subcategoryRequest.getName(), subcategoryName));
    }


// Subcategories with products (without default image)

    @GetMapping(value = "/products")
    @ApiOperation(value = "Get Products in Subcategory")
    public List<ProductResponse> getSubcategoryProducts(@RequestParam String subcategoryName) {
        return ProductMapper.INSTANCE.listToDto(categoryService.findProducts(subcategoryName));
    }

}
