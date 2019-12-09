package com.novko.internal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.JpaSort.JpaOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.novko.OrderNetwork;
import com.novko.internal.cart.Cart;
import com.novko.internal.cart.JpaCarts;
import com.novko.internal.cart.JpaCartsRepository;
import com.novko.internal.customers.Customer;
import com.novko.internal.customers.JpaCustomers;
import com.novko.internal.customers.JpaCustomersRepository;
import com.novko.internal.orders.JpaOrdersRepository;
import com.novko.internal.orders.Order;
import com.novko.internal.products.JpaProducts;
import com.novko.internal.products.JpaProductsRepository;
import com.novko.internal.products.Product;


@Service
public class OrderNetworkImpl implements OrderNetwork {

	private JpaOrdersRepository jpaOrdersRepository;
	private JpaCartsRepository jpaCartsRepository;
	private JpaProductsRepository jpaProductsRepository;
	private JpaCustomersRepository jpaCustomersRepository;
	
	
	@Autowired
	public OrderNetworkImpl(JpaOrdersRepository jpaOrdersRepository, JpaCartsRepository jpaCartsRepository,
							JpaProductsRepository jpaProductsRepository, JpaCustomersRepository jpaCustomersRepository) {
		this.jpaOrdersRepository = jpaOrdersRepository;
		this.jpaCartsRepository = jpaCartsRepository;
		this.jpaProductsRepository = jpaProductsRepository;
		this.jpaCustomersRepository = jpaCustomersRepository;
	}
	
	
//treba u kontroler da se ubaci!!!
	public List<Product> testQuantity(List<Cart> carts) {
		List<Product> products = new ArrayList<>();
		for (Cart cart : carts) {
			if(  jpaProductsRepository.getByName(cart.getProduct().getName()).getQuantity()-cart.getQuantity() < 0) {
				products.add(cart.getProduct());
			}
		}
		
		return products;
	}
	
	
	
//void se menja u PDF class	koja pravi fakturu
	@Transactional
	public void makeOrderForCustomer(List<Cart> carts, String customerName) {
		
		Customer customer = jpaCustomersRepository.getByName(customerName);
		
		if(testQuantity(carts) != null) throw new RuntimeException();
		
		for (Cart cart : carts) {
//			if(  jpaProductsRepository.getByName(cart.getProduct().getName()).getQuantity()-cart.getQuantity() >= 0) {
			
			
			
			jpaProductsRepository.update(cart.getProduct());
			
			
		}
		
		jpaOrdersRepository.save(new Order(carts));
		
		
		
	}
	
	
	
	
	
	
	
	
}
