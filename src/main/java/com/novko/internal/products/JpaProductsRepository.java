package com.novko.internal.products;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public class JpaProductsRepository implements JpaProducts{
	
	
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(JpaProductsRepository.class.getName());

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
	
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
	public Product getByCode(String productCode) {
		return entityManager.createQuery("select p from Product p where p.code = ?1", Product.class).setParameter(1, productCode).getSingleResult();
	}

	@Override
	@Transactional(readOnly = true)
	public Product getByName(String productName) {
		return entityManager.createQuery("select p from Product p where p.name = ?1", Product.class).setParameter(1, productName).getSingleResult();
	}



	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Set<Product> getProducts() {
		return new HashSet<Product>(entityManager.createQuery("from Product").getResultList());
	}






}
