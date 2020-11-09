package com.novko.internal.orders;

import com.novko.internal.cart.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

	@Transactional
	public void addCarts(Order order, List<Cart> carts) {
		order.addCarts(carts);
	}

	@Transactional(readOnly = true)
	public Order findById(Long id) {
		return orderRepository.findById(id).get();
	}

	@Transactional
	public void delete(Order order) {
		orderRepository.delete(order);

	}

	@Transactional(readOnly = true)
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

//	@Override
//	@Transactional(readOnly = true)
//	public List<Order> getAllUnchecked() {
//		return entityManager.createQuery("from Order o where o.status=false").getResultList();
//	}

}
