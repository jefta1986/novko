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
    public CategoryResponse saveCategory(@RequestParam String name, @RequestParam String nameSr) {
        return CategoryMapper.INSTANCE.toDto(categoryService.saveCategory(name, nameSr));
    }

    @PatchMapping(value = "")
    @ApiOperation(value = "Update Category")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponse updateCategory(@RequestBody CategoryRequest categoryRequest) {
        return CategoryMapper.INSTANCE.toDto(categoryService.updateCategory(categoryRequest.getId(), categoryRequest.getName(), categoryRequest.getNameSr()));
    }

    @DeleteMapping(value = "")
    @ApiOperation(value = "Delete Category")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CategoryResponse> deleteCategory(@RequestParam String categoryName) {
        categoryService.deleteByName(categoryName);
        return CategoryMapper.INSTANCE.listToDto(categoryService.findAll());
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

    @GetMapping(value = "/subcategories/all")
    @ApiOperation(value = "Get All Subcategories")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
    public List<SubcategoryResponse> getSubcategories() {
        return SubcategoryMapper.INSTANCE.listToDto(categoryService.findAllSubcategories());
    }


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
    public CategoryResponse addSubcategory(@RequestParam String subcategoryName, @RequestParam String subcategoryNameSr, @RequestParam String categoryName) {
        return CategoryMapper.INSTANCE.toDto(categoryService.addSubcategory(subcategoryName, subcategoryNameSr, categoryName));
    }


    @DeleteMapping(value = "/subcategories")
    @ApiOperation(value = "Delete Subcategory")
    @PreAuthorize("hasRole('ADMIN')")
    public List<SubcategoryResponse> deleteSubcategory(@RequestParam String categoryName, @RequestParam String subcategoryName) {
        categoryService.deleteSubcategory(categoryName, subcategoryName);
        return SubcategoryMapper.INSTANCE.listToDto(categoryService.findSubcategories(categoryName));
    }


    @PatchMapping(value = "/subcategories")
    @ApiOperation(value = "Update Subcategory")
    @PreAuthorize("hasRole('ADMIN')")
    public SubcategoryResponse updateSubcategory(@RequestBody SubcategoryRequest subcategoryRequest, @RequestParam(required = false) String subcategoryName, @RequestParam(required = false) String subcategoryNameSr, @RequestParam(required = true) String categoryName) {
        return SubcategoryMapper.INSTANCE.toDto(categoryService.updateSubcategory(categoryName, subcategoryRequest.getId(), subcategoryRequest.getName(), subcategoryName, subcategoryNameSr));
    }


// Subcategories with products (without default image)

    @GetMapping(value = "/products")
    @ApiOperation(value = "Get Products in Subcategory")
    public List<ProductResponse> getSubcategoryProducts(@RequestParam String subcategoryName) {
        return ProductMapper.INSTANCE.listToDto(categoryService.findProducts(subcategoryName));
    }

//    @GetMapping(value = "/products/page")
//    @ApiOperation(value = "Get Products in Subcategory - by page and Size, Sort default is ASC")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
//    public Page<ProductResponse> getAllProductsInSubcategoryPages(@RequestParam String subcategoryName, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
//        List<ProductResponse> productResponseList = ProductMapper.INSTANCE.listToDto(categoryService.findProductsInSubcategory(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")), subcategoryName));
//        return new PageImpl<>(productResponseList);
//    }

}
