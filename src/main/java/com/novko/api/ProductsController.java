package com.novko.api;

import java.io.IOException;
import java.util.*;

import com.novko.api.mapper.ProductMapper;
import com.novko.api.mapper.ProductWithImagesMapper;
import com.novko.api.request.ProductRequest;
import com.novko.api.request.UpdateProductRequest;
import com.novko.api.response.ProductResponse;
import com.novko.api.response.ProductWithImagesResponse;
import com.novko.internal.categories.CategoryService;
import com.novko.internal.products.*;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/rest/products")
public class ProductsController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final ImagesService imagesService;

    @Autowired
    public ProductsController(CategoryService categoryService, ProductService productService, ImagesService imagesService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.imagesService = imagesService;
    }


    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Save Product data - without images")
    @PreAuthorize("hasRole('ADMIN')")
//	@CacheEvict(value = "product", key = "#product.code")    da li po code ili name ??
    public ProductResponse saveProduct(@RequestBody ProductRequest productRequest) {
        return ProductMapper.INSTANCE.toDto(productService.save(productRequest.getName(), productRequest.getCode(), productRequest.getBrand(), productRequest.getDescription(), productRequest.getAmountDin(), productRequest.getAmountEuro(), productRequest.getQuantity()));
    }

    @PutMapping(value = "")
    @ApiOperation(value = "Update Product")
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse updateProduct(@RequestBody UpdateProductRequest productRequest) {
        return ProductMapper.INSTANCE.toDto(productService.update(productRequest.getId(), productRequest.getName(), productRequest.getCode(), productRequest.getBrand(), productRequest.getDescription(), productRequest.getAmountDin(), productRequest.getAmountEuro(), productRequest.getQuantity(), productRequest.getEnabled()));
    }

    @GetMapping(value = "/id/{id}")
    @ApiOperation(value = "Get Product by Id")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
//	@Cacheable(value = "product", key = "#productCode")
    public ProductResponse getProductById(@PathVariable("id") Long id) {
        return ProductMapper.INSTANCE.toDto(productService.findById(id));
    }

    //Product with default image only !!!
    @GetMapping(value = "/{name}")
    @ApiOperation(value = "Get Product by Name")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
    public ProductResponse getProductByName(@PathVariable("name") String name) {
        return ProductMapper.INSTANCE.toDto(productService.findByName(name));
    }

    @GetMapping(value = "/code")
    @ApiOperation(value = "Get Product by Code")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
//	@Cacheable(value = "product", key = "#productCode")
    public ProductResponse getProductByCode(@RequestParam String code) {
        return ProductMapper.INSTANCE.toDto(productService.findByCode(code));
    }

    @DeleteMapping(value = "")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete Product By Name")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProductByName(@RequestParam String name) {
        productService.deleteByName(name);
    }

    @DeleteMapping(value = "/id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete Product By Id")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProductById(@RequestParam Long id) {
        productService.deleteById(id);
    }


    // add product to subcategory
    @PostMapping(value = "/add")
    @ApiOperation(value = "Add Product to Subcategory")
    @PreAuthorize("hasRole('ADMIN')")
//	@CacheEvict(value = "") da li za product ili subcategory cache dodati ??
    public ResponseEntity<String> addProductToSubcategory(@RequestParam String subcategoryName, @RequestParam String productName) {
        Product product = productService.findByName(productName);
        categoryService.addProductToSubcategory(subcategoryName, product);
        return new ResponseEntity<String>("product added to subcategory", HttpStatus.OK);
    }

    @GetMapping(value = "/images")
    @ApiOperation(value = "Get All Products with product Images")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
    public List<ProductWithImagesResponse> getAllProductsWithImages() {
        return ProductWithImagesMapper.INSTANCE.listToDto(productService.findAll());
    }

    // set default picture for product (id)
    @PostMapping(value = "/default")
    @ApiOperation(value = "Set Default Image to Product")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> setDefaultImage(@RequestParam(value = "productId") Long productId, @RequestParam(value = "imageId") Long imageId) {
        imagesService.setDefaultPicture(productId, imageId);
        return new ResponseEntity<String>("Set default picture", HttpStatus.OK);
    }


    @PostMapping(value = "/{id}/image")
    @ApiOperation(value = "Add Image to Product")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addImageToProduct(@RequestParam(value = "file") MultipartFile multipartFile, @PathVariable(value = "id") Long productId) throws IOException {
        byte[] data = IOUtils.toByteArray(multipartFile.getInputStream());
        Product product = productService.findById(productId);

        if (product != null) {
            Images image = new Images();
            image.setName(multipartFile.getOriginalFilename());
            image.setType(multipartFile.getContentType());
            image.setData(data);
            image.addProduct(product);

            imagesService.save(image, productId);
            return new ResponseEntity<String>("Image added to Product", HttpStatus.OK);
        }

        return new ResponseEntity<String>("product is not in database", HttpStatus.OK);
    }

    @PostMapping(value = "/image")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> removeImage(@RequestParam(value = "imageId") Long imageId) {
        imagesService.deleteById(imageId);
        return new ResponseEntity<String>("image removed", HttpStatus.OK);
    }

}
