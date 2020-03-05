package com.novko.internal.orders;


import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.novko.internal.cart.Cart;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public class JpaOrdersRepository implements JpaOrders{

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
	@Transactional
	public void update(Order order) {
		entityManager.merge(order);
	}

	@Override
	@Transactional
	public void addCarts(Order order, List<Cart> carts) {
		order.addCarts(carts);
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



	@Override
	@Transactional(readOnly = true)
	public List<Order> getAll() {
		return entityManager.createQuery("from Order o").getResultList();
	}


	@Override
	@Transactional(readOnly = true)
	public List<Order> getAllUnchecked() {
		return entityManager.createQuery("from Order o where o.status=false").getResultList();
	}


		
	
	

}
