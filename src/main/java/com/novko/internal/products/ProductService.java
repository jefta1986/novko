package com.novko.internal.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


   @Transactional
   public void deleteByName(String name){
       productRepository.deleteByName(name);
   }

    @Transactional
    public void setDefaultPicture(Long productId, Long imageId){
        Product product = productRepository.findById(productId).get();
        for (Images image : product.getImages()) {
            if (image.getDefaultPicture().equals(Boolean.TRUE))
                image.setDefaultPicture(Boolean.FALSE);

            if (image.getId().equals(imageId))
                image.setProduct(product);
        }
    }

}
