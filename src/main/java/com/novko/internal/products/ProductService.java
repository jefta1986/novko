package com.novko.internal.products;

import com.novko.api.exception.CustomResourceNotFoundException;
import com.novko.internal.categories.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }


    @Transactional
    public void deleteByCode(String code) {
        productRepository.deleteByCode(code);
    }

    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Product findById(Long productId) {
        return productRepository.getOne(productId);
    }

    @Transactional(readOnly = true)
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Product findByCode(String code) {
        return productRepository.findByCode(code);
    }

    @Transactional(readOnly = true)
    public List<Product> findAllWhereSubcategoryIsNull() {
        return productRepository.findBySubcategoryIsNull();
    }


    //proveri sta se tacno salje od podataka
    @Transactional
    public Product save(String name, String code, String brand, String description, String descriptionSr,Integer amountDin, Integer amountEur, Integer quantity, String subcategoryName) {
        Product product = new Product();
        product.setEnabled(Boolean.TRUE);

        if (name != null && !name.isEmpty()) {
            product.setName(name);
        }
        if (code != null && !code.isEmpty()) {
            product.setCode(code);
        }
        if (brand != null && !brand.isEmpty()) {
            product.setBrand(brand);
        }
        if (description != null && !description.isEmpty()) {
            product.setDescription(description);
        }
        if (descriptionSr != null && !descriptionSr.isEmpty()) {
            product.setDescriptionSr(descriptionSr);
        }
        if (amountDin != null) {
            product.setAmountDin(amountDin);
        }
        if (amountEur != null) {
            product.setAmountEuro(amountEur);
        }
        if (quantity != null) {
            product.setQuantity(quantity);
        }

        Product productDb = productRepository.save(product);
        categoryService.addProductToSubcategory(subcategoryName, productDb);
        return productDb;
    }

    @Transactional
    public Product update(Long id, String name, String code, String brand, String description, String descriptionSr,Integer amountDin, Integer amountEur, Integer quantity, Boolean enabled, String subcategoryName) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (!optionalProduct.isPresent()) {
            throw new CustomResourceNotFoundException("Product is not in database");
        }

        Product product = optionalProduct.get();

        if(enabled != null) {
            product.setEnabled(enabled);
        }
        if (name != null && !name.isEmpty()) {
            product.setName(name);
        }
        if (code != null && !code.isEmpty()) {
            product.setCode(code);
        }
        if (brand != null && !brand.isEmpty()) {
            product.setBrand(brand);
        }
        if (description != null && !description.isEmpty()) {
            product.setDescription(description);
        }
        if (descriptionSr != null && !descriptionSr.isEmpty()) {
            product.setDescriptionSr(descriptionSr);
        }
        if (amountDin != null) {
            product.setAmountDin(amountDin);
        }
        if (amountEur != null) {
            product.setAmountEuro(amountEur);
        }
        if (quantity != null) {
            product.setQuantity(quantity);
        }

        Product productDb = productRepository.save(product);
        categoryService.addProductToSubcategory(subcategoryName, productDb);
        return productDb;
    }

}
