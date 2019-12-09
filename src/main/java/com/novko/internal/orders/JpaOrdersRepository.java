package com.novko.internal.orders;


import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public class JpaOrdersRepository implements JpaOrders{

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(JpaOrdersRepository.class.getName());

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	


	@Override
	@Transactional
	public void save(Order order) {
		entityManager.persist(order);
	}


	@Override
	@Transactional(readOnly = true)
	public Order get(Long id) {
		return entityManager.find(Order.class, id);
	}



	@Override
	@Transactional
	public void delete(Order order) {
		entityManager.remove(order);

	}



	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Order> getAll() {
		return entityManager.createQuery("from Order o").getResultList();
	}


		
	
	

}
