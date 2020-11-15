package com.novko.internal.orders;

import com.novko.internal.cart.Cart;
import com.novko.internal.cart.CartService;
import com.novko.internal.products.Product;
import com.novko.internal.products.ProductService;
import com.novko.security.User;
import com.novko.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
	private final CartService cartService;
	private final UserService userService;

	@Autowired
	public OrderService(OrderRepository orderRepository, ProductService productService, CartService cartService, UserService userService) {
		this.orderRepository = orderRepository;
		this.productService = productService;
		this.cartService = cartService;
		this.userService = userService;
	}


	@Transactional
	public void save(Order order) {
		orderRepository.save(order);
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

	@Transactional
	public void deleteById(Long id) {
		orderRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Transactional
	public void createOrder(List<Cart> carts, boolean status, String username, String name, String surname, String phoneNumber, String country, String city, String address, String postalCode, String description) throws RuntimeException {
		for (Cart cart : carts) {
			Product productFromDb = productService.findByName(cart.getProduct().getName());
			Integer productQuantityDb = productFromDb.getQuantity();
			Integer cartQuantity = cart.getQuantity();
			if (cartQuantity > productQuantityDb)
				throw new RuntimeException("Product is anymore in stock: " + cart.getProduct().getName());
		}

		Order order = new Order(carts, false, name, surname, phoneNumber, country, city, address, postalCode, description);

		User user = userService.findByUsername(username);
		order.setUser(user);
//		this.save(order);

		for (Cart cart : carts) {
			Product productFromDb = productService.findByName(cart.getProduct().getName());
			Integer productQuantityDb = productFromDb.getQuantity();
			Integer cartQuantity = cart.getQuantity();

			productFromDb.setQuantity(productQuantityDb - cartQuantity);
//			productService.save(productFromDb.getName(), productFromDb.getCode(), productFromDb.getBrand(), productFromDb.getDescription(), productFromDb.getAmountDin(), productFromDb.getAmountEuro(), productFromDb.getQuantity());  //update entity-merge

			cart.setOrder(order);

			//iznos ukupni za npr 2 narucena ista proizvoda: quantity tj. 2*cenaDin
			//treba logika za Sr/En
			cart.setAmountDin(cartQuantity * cart.getProduct().getAmountDin());
			cart.setAmountEuro(cartQuantity * cart.getProduct().getAmountEuro());
			order.saveQuantity();
			order.saveAmountDin();
			order.saveAmountEuro();
			cartService.save(cart);
			this.save(order);
		}
    }


//	@Override
//	@Transactional(readOnly = true)
//	public List<Order> getAllUnchecked() {
//		return entityManager.createQuery("from Order o where o.status=false").getResultList();
//	}

}
