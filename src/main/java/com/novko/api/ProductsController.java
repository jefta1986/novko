package com.novko.api;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.novko.internal.categories.CategoryRepository;
import com.novko.internal.categories.CategoryService;
import com.novko.internal.dto.ImagesDto;
import com.novko.internal.dto.ProductDto;
import com.novko.internal.dto.ProductWithImagesDto;
import com.novko.internal.products.*;
import org.apache.commons.io.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/rest/products")
public class ProductsController {

    private final CategoryService categoryService;
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final CategoryRepository categoryRepository;
    private final ImagesRepository imagesRepository;
    private final ImagesService imagesService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductsController(CategoryService categoryService, ProductRepository productRepository, ProductService productService, CategoryRepository categoryRepository, ImagesRepository imagesRepository, ImagesService imagesService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.productRepository = productRepository;
        this.productService = productService;
        this.categoryRepository = categoryRepository;
        this.imagesRepository = imagesRepository;
        this.imagesService = imagesService;
        this.modelMapper = modelMapper;
    }



    @GetMapping(value = "")
    public List<ProductWithImagesDto> getAllProductsWithImages() {

        List<Product> products = productRepository.findAll();

        List<ProductWithImagesDto> productDtoList = products.stream().map(product -> modelMapper.map(product, ProductWithImagesDto.class)).collect(Collectors.toList());

        return productDtoList;
    }

    //proizvodi bez slika
    @GetMapping(value = "/all")
    public List<ProductDto> getAllProducts() {

        List<Product> products = productRepository.findAll();

        List<ProductDto> productDtoList = products.stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());

        return productDtoList;
    }


    //HOME stranica: gde se prikazuju proizvodi sa defaultnom slikom !
    @GetMapping(value = "/default")
    public List<ProductDto> getAllProductsWithDefaultImages() {

        List<Product> products = productRepository.findAllWithDefaultImage();
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : products) {
            ProductDto productDto = modelMapper.map(product, ProductDto.class);
            ImagesDto imageDto = modelMapper.map(product.getImages().get(0), ImagesDto.class);
            productDto.setImage(imageDto);
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    // set default picture for product (id)
    @PostMapping(value = "/default")
    public ResponseEntity<String> setDefaultImage(@RequestParam(value = "productId") Long productId, @RequestParam(value = "imageId") Long imageId) {
        imagesService.setDefaultPicture(productId, imageId);
        return new ResponseEntity<String>("Set default picture", HttpStatus.OK);
    }


    //Product with default image only !!!
    @GetMapping(value = "/{name}")
    public ProductDto getProductByName(@PathVariable String name) {
        Product product = productRepository.findByName(name);
        return modelMapper.map(product, ProductDto.class);
    }

    @GetMapping(value = "/code")
//	@Cacheable(value = "product", key = "#productCode")
    public ProductDto getProductByCode(@RequestParam String code) {
        Product product = productRepository.findByCode(code);
        return modelMapper.map(product, ProductDto.class);
    }


    // add product to subcategory
    @PostMapping(value = "/add")
//	@CacheEvict(value = "") da li za product ili subcategory cache dodati ??
    public ResponseEntity<String> addProductToSubcategory(@RequestParam String subcategoryName, @RequestParam String productName) {
        Product product = productRepository.findByName(productName);
        categoryService.addProductToSubcategory(subcategoryName, product);
        return new ResponseEntity<String>("product added to subcategory", HttpStatus.OK);
    }


    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
//	@CacheEvict(value = "product", key = "#product.code")    da li po code ili name ??
    public void saveProduct(@RequestBody Product product) {
        productRepository.save(product);
    }

    @PostMapping(value = "/{id}/addImage")
    public ResponseEntity<String> addImageToProduct(@RequestParam(value = "file") MultipartFile multipartFile, @PathVariable(value = "id") Long productId) throws IOException {
        byte[] data = IOUtils.toByteArray(multipartFile.getInputStream());
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            Images image = new Images();
            image.setName(multipartFile.getOriginalFilename());
            image.setType(multipartFile.getContentType());
            image.setData(data);
            image.addProduct(optionalProduct.get());

            imagesService.save(image, productId);
            return new ResponseEntity<String>("Image added to Product", HttpStatus.OK);
        }

        return new ResponseEntity<String>("product is not in database", HttpStatus.OK);
    }

    @PostMapping(value = "/removeImage")
    public ResponseEntity<String> removeImage(@RequestParam(value = "imageId") Long imageId) {
        imagesRepository.deleteById(imageId);
        return new ResponseEntity<String>("image removed", HttpStatus.OK);
    }


    @PutMapping(value = "/updateImage")
    public ResponseEntity<String> updateImage(@RequestParam(value = "imageId") Long imageId) {
        Images image = imagesRepository.findById(imageId).get();
        imagesRepository.save(image);
        return new ResponseEntity<String>("image updated", HttpStatus.OK);
    }


    @DeleteMapping(value = "")
    public ResponseEntity<String> deleteProduct(@RequestParam String name) {
        Product product = productRepository.findByName(name);
        if (product == null) {
            return new ResponseEntity<String>("product doesn't exists", HttpStatus.OK);
        }

        productService.deleteByName(name);
        return new ResponseEntity<String>("product deleted", HttpStatus.OK);
    }

}
