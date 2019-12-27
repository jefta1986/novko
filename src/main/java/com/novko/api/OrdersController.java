package com.novko.api;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.novko.internal.cart.Cart;
import com.novko.internal.cart.JpaCartsRepository;
import com.novko.internal.orders.JpaOrdersRepository;
import com.novko.internal.orders.Order;

@RestController
@RequestMapping("/rest/orders")
public class OrdersController {
	
	private JpaOrdersRepository jpaOrdersRepository;
	private JpaCartsRepository jpaCartsRepository;
	
	
	@Autowired
	public void setJpaOrdersRepository(JpaOrdersRepository jpaOrdersRepository) {
		this.jpaOrdersRepository = jpaOrdersRepository;
	}


	@Autowired
	public void setJpaCartsRepository(JpaCartsRepository jpaCartsRepository) {
		this.jpaCartsRepository = jpaCartsRepository;
	}


	@PostMapping(value = "")
	public ResponseEntity<String> save(HttpSession session) {

		List<Cart> carts = (List<Cart>) session.getAttribute("cart");

		Order order = Order.factory(carts);
		jpaOrdersRepository.save(order);

		for (Cart cart : carts) {
			cart.setOrder(order);
			jpaCartsRepository.save(cart);
		}
		return new ResponseEntity<String>("order saved", HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
		return new ResponseEntity<Order>(jpaOrdersRepository.get(id), HttpStatus.OK);
	}
	
	
	
	@GetMapping(value = "")
	public ResponseEntity<List<Order>> getOrders() {
		return new ResponseEntity<List<Order>>(jpaOrdersRepository.getAll(), HttpStatus.OK);
	}

}
