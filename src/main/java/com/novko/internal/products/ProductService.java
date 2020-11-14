package com.novko.internal.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Transactional
    public void deleteByName(String name) {
        productRepository.deleteByName(name);
    }

    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void setDefaultPicture(Long productId, Long imageId) {
        Product product = productRepository.findById(productId).get();
        for (Images image : product.getImages()) {
            if (image.getDefaultPicture().equals(Boolean.TRUE))
                image.setDefaultPicture(Boolean.FALSE);

            if (image.getId().equals(imageId))
                image.setProduct(product);
        }
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Transactional(readOnly = true)
    public Product findByCode(String code) {
        return productRepository.findByCode(code);
    }


    //proveri sta se tacno salje od podataka
    @Transactional
    public Product save(String name, String code, String brand, String description, Integer amountDin, Integer amountEur, Integer quantity) {
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
        if (amountDin != null) {
            product.setAmountDin(amountDin);
        }
        if (amountEur != null) {
            product.setAmountEuro(amountEur);
        }
        if (quantity != null) {
            product.setQuantity(quantity);
        }

        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public Product findById(Long productId) {
        return productRepository.getOne(productId);
    }

    @Transactional
    public Product update(Long id, String name, String code, String brand, String description, Integer amountDin, Integer amountEur, Integer quantity, Boolean enabled) {
        Product product = productRepository.getOne(id);

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
        if (amountDin != null) {
            product.setAmountDin(amountDin);
        }
        if (amountEur != null) {
            product.setAmountEuro(amountEur);
        }
        if (quantity != null) {
            product.setQuantity(quantity);
        }

        return productRepository.save(product);
    }

}
