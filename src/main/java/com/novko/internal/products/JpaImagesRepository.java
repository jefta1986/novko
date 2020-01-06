package com.novko.internal.products;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JpaImagesRepository implements JpaImages {


    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void setDefault(Long productId, Long imageId) {
        Images defaultImage = entityManager.find(Images.class, imageId);

        defaultImage.setDefaultPicture(productId);
        entityManager.merge(defaultImage);
    }

    @Override
    @Transactional(readOnly = true)
    public Images getById(Long imageId) {
        return entityManager.find(Images.class, imageId);
    }


    //    @Override
//    @Transactional
//    public void add(Product product) {
//        entityManager.persist(product);
//
//    }

}
