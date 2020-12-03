package com.novko.api;


import com.novko.api.exception.CustomIllegalArgumentException;
import com.novko.api.mapper.ProductMapper;
import com.novko.api.request.CreateProductRequest;
import com.novko.api.request.UpdateProductRequest;
import com.novko.api.response.ProductResponse;
import com.novko.internal.categories.CategoryService;
import com.novko.internal.products.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/rest/products")
public class ProductsController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public ProductsController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }


    @GetMapping(value = "")
    @ApiOperation(value = "Get All Products - with List<String> imagePathList")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
    public List<ProductResponse> getAllProducts() {
        return ProductMapper.INSTANCE.listToDto(productService.findAll());
    }

    @GetMapping(value = "/isnull")
    @ApiOperation(value = "Get All Products who doesn't have Subcategory")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ProductResponse> getAllWhereSubcategoryIsNull() {
        return ProductMapper.INSTANCE.listToDto(productService.findAllWhereSubcategoryIsNull());
    }

//    @GetMapping(value = "/page")
//    @ApiOperation(value = "Get All Products - by page and Size, Sort default is ASC")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
//    public Page<ProductResponse> getAllProductsPageable(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
//        List<ProductResponse> productResponseList = ProductMapper.INSTANCE.listToDto(productService.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"))).getContent());
//        return new PageImpl<>(productResponseList);
//    }


    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Save Product data - without images")
    @PreAuthorize("hasRole('ADMIN')")
//	@CacheEvict(value = "product", key = "#product.code")
    public ProductResponse saveProduct(@RequestBody CreateProductRequest productRequest) {
        Product product = productService.save(productRequest.getName(), productRequest.getCode(), productRequest.getBrand(), productRequest.getDescription(), productRequest.getDescriptionSr(), productRequest.getAmountDin(), productRequest.getAmountEuro(), productRequest.getQuantity(), productRequest.getSubcategoryName());
        return ProductMapper.INSTANCE.toDto(product);
    }

    @PutMapping(value = "")
    @ApiOperation(value = "Update Product - without images")
    @PreAuthorize("hasRole('ADMIN')")
    public ProductResponse updateProduct(@RequestBody UpdateProductRequest productRequest) {
        return ProductMapper.INSTANCE.toDto(productService.update(productRequest.getId(), productRequest.getName(), productRequest.getCode(), productRequest.getBrand(), productRequest.getDescription(), productRequest.getDescriptionSr(), productRequest.getAmountDin(), productRequest.getAmountEuro(), productRequest.getQuantity(), productRequest.getEnabled(), productRequest.getSubcategoryName()));
    }

    @PostMapping(value = "/upload")
//    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Save Image file on disk - add image to Product")
    @PreAuthorize("hasRole('ADMIN')")
//	@CacheEvict(value = "product", key = "#product.code")
    public ResponseEntity<Object> saveImage(@RequestParam Long productId, @RequestParam("file") MultipartFile file) {
        Product product = null;
        try {
            product = productService.saveImageOnDisk(productId, file);
        } catch (IOException e) {
            return new ResponseEntity<>("IOException", HttpStatus.OK);
        }
        return new ResponseEntity<>(ProductMapper.INSTANCE.toDto(product), HttpStatus.OK);
    }

    @DeleteMapping(value = "/upload/delete")
//    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Delete Image file from disk - remove image from Product")
    @PreAuthorize("hasRole('ADMIN')")
//	@CacheEvict(value = "product", key = "#product.code")
    public ResponseEntity<Object> deleteImage(@RequestParam("productId") Long productId, @RequestParam("fileName") String fileName) {
        Product product = null;
        try {
            product = productService.deleteImage(productId, fileName);
        } catch (IOException e) {
            return new ResponseEntity<>("IOException", HttpStatus.OK);
        }
        return new ResponseEntity<>(ProductMapper.INSTANCE.toDto(product), HttpStatus.OK);
    }

    @GetMapping(value = "/id/{id}")
    @ApiOperation(value = "Get Product by Id")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
//	@Cacheable(value = "product", key = "#productCode")
    public ProductResponse getProductById(@PathVariable("id") Long id) {
        return ProductMapper.INSTANCE.toDto(productService.findById(id));
    }

    //Product with default image only !!!
    @GetMapping(value = "/{code}")
    @ApiOperation(value = "Get Product by Code")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
    public ProductResponse getProductByCode(@PathVariable("code") String code) {
        return ProductMapper.INSTANCE.toDto(productService.findByCode(code));
    }

//    @GetMapping(value = "/name")
//    @ApiOperation(value = "Get Product by Name")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
////	@Cacheable(value = "product", key = "#productCode")
//    public ProductResponse getProductByName(@RequestParam String productName) {
//        return ProductMapper.INSTANCE.toDto(productService.findByName(productName));
//    }

//    @DeleteMapping(value = "")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @ApiOperation(value = "Delete Product By Code")
//    @PreAuthorize("hasRole('ADMIN')")
//    public void deleteProductByCode(@RequestParam String code) {
//        productService.deleteByCode(code);
//    }

    @DeleteMapping(value = "/id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete Product By Id")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProductById(@RequestParam Long id) {
        try {
            productService.deleteById(id);

        } catch (IOException e) {
            throw new CustomIllegalArgumentException("IO Exception");
        }
    }


    // add product to subcategory
//    @PostMapping(value = "/add")
//    @ApiOperation(value = "Add Product to Subcategory")
//    @PreAuthorize("hasRole('ADMIN')")
////	@CacheEvict(value = "") da li za product ili subcategory cache dodati ??
//    public ResponseEntity<String> addProductToSubcategory(@RequestParam String subcategoryName, @RequestParam String productCode) {
//        Product product = productService.findByCode(productCode);
//        categoryService.addProductToSubcategory(subcategoryName, product);
//        return new ResponseEntity<String>("product added to subcategory", HttpStatus.OK);
//    }

//    @GetMapping(value = "/images")
//    @ApiOperation(value = "Get All Products with product Images")
//    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
//    public List<ProductWithImagesResponse> getAllProductsWithImages() {
//        return ProductWithImagesMapper.INSTANCE.listToDto(productService.findAll());
//    }

    // set default picture for product (id)
//    @PostMapping(value = "/default")
//    @ApiOperation(value = "Set Default Image to Product")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<String> setDefaultImage(@RequestParam(value = "productId") Long productId, @RequestParam(value = "imageId") Long imageId) {
//        imagesService.setDefaultPicture(productId, imageId);
//        return new ResponseEntity<String>("Set default picture", HttpStatus.OK);
//    }


//    @PostMapping(value = "/{id}/image")
//    @ApiOperation(value = "Add Image to Product")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<String> addImageToProduct(@RequestParam(value = "file") MultipartFile multipartFile, @PathVariable(value = "id") Long productId) throws IOException {
//        byte[] data = IOUtils.toByteArray(multipartFile.getInputStream());
//        Product product = productService.findById(productId);
//
//        if (product != null) {
//            Images image = new Images();
//            image.setName(multipartFile.getOriginalFilename());
//            image.setType(multipartFile.getContentType());
//            image.setData(data);
//            image.addProduct(product);
//
//            imagesService.save(image, productId);
//            return new ResponseEntity<String>("Image added to Product", HttpStatus.OK);
//        }
//
//        return new ResponseEntity<String>("product is not in database", HttpStatus.OK);
//    }
//
//    @PostMapping(value = "/image")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<String> removeImage(@RequestParam(value = "imageId") Long imageId) {
//        imagesService.deleteById(imageId);
//        return new ResponseEntity<String>("image removed", HttpStatus.OK);
//    }

}
