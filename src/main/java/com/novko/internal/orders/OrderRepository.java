package com.novko.internal.orders;

import com.novko.internal.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

//	void save(Order order);
//	void update(Order order);
//	void addCarts(Order order, List<Cart> carts);
//	void delete(Order order);
//    Order get(Long id);
//    List<Order> getAll();
//	List<Order> getAllUnchecked();


}
