package com.novko.internal.customers;



import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




@Repository
public class JpaCustomersRepository implements JpaCustomers {

//	public static final String INFO = "JpaUserRepository";
	
	private static final Logger log = Logger.getLogger(JpaCustomersRepository.class.getName());
	

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	
	
	@Override
	@Transactional
	public void add(Customer customer) {
		entityManager.persist(customer);
		log.info("Customer: [  " + customer );
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Customer> getAll() {
		return entityManager.createQuery("from Customer").getResultList();
	}



	@Override
	@Transactional(readOnly = true)
	public Customer getByEmail(String email) {
		return entityManager.createQuery("select c from Customer c where c.email = ?1", Customer.class).setParameter(1, email).getSingleResult();
	}



	@Override
	@Transactional(readOnly = true)
	public Customer getByName(String name) {
		return entityManager.createQuery("select c from Customer c where c.name = ?1", Customer.class).setParameter(1, name).getSingleResult();
	}

}
