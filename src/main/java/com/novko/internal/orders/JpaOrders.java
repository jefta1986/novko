package com.novko.internal.orders;

import java.util.List;


public interface JpaOrders {

	
	void save(Order order);
	void delete(Order order);
    Order get(Long id);
    List<Order> getAll();

	
}
