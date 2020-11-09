package com.novko.internal.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ImagesService {

    private final ImagesRepository imagesRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ImagesService(ImagesRepository imagesRepository, ProductRepository productRepository) {
        this.imagesRepository = imagesRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public void save(Images image, Long productId) {
        Product product = productRepository.getOne(productId);
        image.setProduct(product);
        imagesRepository.save(image);
    }


    //prethodnu defaultnu treba postaviti na FALSE
//    @Transactional
//    public void setDefaultImage(Long imageId, Long productId) {
//        Images image = imagesRepository.getOne(imageId);
//        image.s
//
//        image.setDefaultPicture(Boolean.TRUE);
//        imagesRepository.save(image);
//    }


    @Transactional
    public void deleteByName(Long id) {
        imagesRepository.deleteById(id);
    }

    //proveri
    @Transactional
    public void setDefaultPicture(Long productId, Long imageId) {
        Product product = productRepository.getOne(productId);

        Images image = imagesRepository.findDefaultImageByProductId(productId);
        image.setDefaultPicture(Boolean.FALSE);

        imagesRepository.getOne(imageId).setProduct(product);
    }


}
