package com.novko.internal.orders;

import com.novko.internal.cart.Cart;

import java.util.List;


public interface JpaOrders {

	
	void save(Order order);
	void addCarts(Order order, List<Cart> carts);
	void delete(Order order);
    Order get(Long id);
    List<Order> getAll();

	
}
