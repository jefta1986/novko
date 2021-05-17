package com.novko.api;

import com.novko.api.mapper.CategoryMapper;
import com.novko.api.mapper.ProductMapper;
import com.novko.api.response.CategoryResponse;
import com.novko.api.response.HomeResponse;
import com.novko.api.response.ProductResponse;
import com.novko.internal.categories.CategoryService;
import com.novko.internal.products.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public HomeController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }


    @GetMapping(value = "/user")
    @ApiOperation(value = "check if user logged like USER")
    @PreAuthorize("hasRole('USER')")
    public String user() {
        return "<h1>User Page</h1>";
    }

    @GetMapping(value = "/admin")
    @ApiOperation(value = "check if user logged like ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "<h1>Admin Page</h1>";
    }

    @GetMapping(value = "")
    @ApiOperation(value = "Home page: get all Categories and Products")
    @PreAuthorize("hasRole('USER') or isAnonymous()")
    public HomeResponse homeResponse() {
        List<CategoryResponse> categories = CategoryMapper.INSTANCE.listToDto(categoryService.findAll());
        List<ProductResponse> products = ProductMapper.INSTANCE.listToDto(productService.findAll());

        return new HomeResponse(categories, products);
    }

}
