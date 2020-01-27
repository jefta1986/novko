package com.novko.internal.products;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.novko.internal.dto.ProductWithImagesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class JpaProductsRepository implements JpaProducts {


    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(JpaProductsRepository.class.getName());

    private EntityManager entityManager;
//    private CacheManager cacheManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

//    @Autowired
//    public void setCacheManager(CacheManager cacheManager) {
//        this.cacheManager = cacheManager;
//    }

    @Override
    @Transactional
    public void add(Product product) {
        entityManager.persist(product);

    }


    @Override
    @Transactional
    public void update(Product product) {
        entityManager.merge(product);
    }


    @Override
    @Transactional
    public void delete(Product product) {
        entityManager.remove(product);
    }


    @Override
    @Transactional(readOnly = true)
    public Product getById(Long productId) {
        return entityManager.find(Product.class, productId);
    }

    @Override
    @Transactional(readOnly = true)
    public Product getByCode(String productCode) {
        return entityManager.createQuery("select p from Product p where p.code = ?1", Product.class).setParameter(1, productCode).getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public Product getByName(String productName) {
        return entityManager.createQuery("select p from Product p where p.name = ?1", Product.class).setParameter(1, productName).getSingleResult();
    }

// Product with Images
    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "productWithImagesCache",key = "#root.methodName")
    public Product getProductWithImages(String productName) {
        return entityManager.createQuery("select p from Product p left join fetch p.images i where p.name = ?1", Product.class).setParameter(1, productName).getSingleResult();
    }


    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "productsCache",key = "#root.methodName")
    public Set<Product> getProducts() {
        Set<Product> products = new HashSet<Product>(entityManager.createQuery("from Product").getResultList());
        return products;
    }



    //delete soon!
    @Override
	@Transactional(readOnly = true)
    @Cacheable(cacheNames = "productsWithImagesCache",key = "#root.methodName")
	public Set<Product> getProductsWithImages() {
//        Cache cache = cacheManager.getCache("productsWithImagesCache");
		Set<Product> products = new HashSet<>(entityManager.createQuery("select p from Product p left join fetch p.images i").getResultList());
//        cache.put(2L, products);
		return products;
	}
//    @Override
//    @Transactional(readOnly = true)
//    public List<Product> getProductsWithImages() {
//        List<Product> products = entityManager.createQuery("select p from Product p left join fetch p.images").getResultList();
//        return products;
//    }

}
