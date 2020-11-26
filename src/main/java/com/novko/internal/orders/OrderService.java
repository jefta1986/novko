package com.novko.internal.orders;

import com.novko.api.exception.CustomIllegalArgumentException;
import com.novko.internal.cart.Cart;
import com.novko.internal.cart.CartService;
import com.novko.internal.products.Product;
import com.novko.internal.products.ProductService;
import com.novko.security.User;
import com.novko.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Transactional
    public Order updateStatusToTrue(Long id) {
        Order order = orderRepository.findById(id).get();
        order.setStatus(Boolean.TRUE);
        return order;
    }

    @Transactional
    public void addCarts(Order order, List<Cart> carts) {
        order.addCarts(carts);
    }

    @Transactional(readOnly = true)
    public Order findById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Order> findByStatusFalse() {
        return orderRepository.findByStatusFalse();
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

//    @Transactional
//    public void createOrder(List<Cart> carts, boolean status, String username, String name, String surname, String phoneNumber, String country, String city, String address, String postalCode, String description) throws RuntimeException {
//        for (Cart cart : carts) {
//            Product productFromDb = productService.findByName(cart.getProduct().getName());
//            Integer productQuantityDb = productFromDb.getQuantity();
//            Integer cartQuantity = cart.getQuantity();
//            if (cartQuantity > productQuantityDb)
//                throw new RuntimeException("Product is anymore in stock: " + cart.getProduct().getName());
//        }
//
//        Order order = new Order(carts, false, name, surname, phoneNumber, country, city, address, postalCode, description);
//
//        User user = userService.findByUsername(username);
////        order.addUser(user);
////        userService.save(user);
//        order.setUser(user);
//
//        Iterator<Cart> cartIterator = carts.iterator();
//        while (cartIterator.hasNext()) {
////        for (Cart cart : cartIterator) {
//            Cart cart = cartIterator.next();
//
//            Product productFromDb = productService.findByName(cart.getProduct().getName());
//            Integer productQuantityDb = productFromDb.getQuantity();
//            Integer cartQuantity = cart.getQuantity();
//
//            productFromDb.setQuantity(productQuantityDb - cartQuantity);
//
////            cart.addOrder(order);
//            cart.setOrder(order);
//
//            //iznos ukupni za npr 2 narucena ista proizvoda: quantity tj. 2*cenaDin
//            //treba logika za Sr/En
//            cart.setAmountDin(cartQuantity * cart.getProduct().getAmountDin());
//            cart.setAmountEuro(cartQuantity * cart.getProduct().getAmountEuro());
//            order.saveQuantity();
//            order.saveAmountDin();
//            order.saveAmountEuro();
//            cartService.save(cart);
//            order.getCarts().add(cart);
////            cartService.save(cart);
//            this.save(order);
//        }
////        order.addUser(user);
//
////        this.save(order);
//        order.setUser(user);
//        this.save(order);
//        userService.save(user);
////        return this.save(order);
//    }

    @Transactional(readOnly = true)
    public List<String> validateProducts(Map<String, Integer> productInCart) {
        List<String> unvalidateProducts = new ArrayList<>();

        Set<String> keys = productInCart.keySet();
        for (String productName : keys) {
            Product productFromDb = productService.findByName(productName);
            Integer productQuantityDb = productFromDb.getQuantity();
            Integer cartQuantity = productInCart.get(productName);
            if (cartQuantity > productQuantityDb) {
                unvalidateProducts.add(productName);
            }
        }

        if (unvalidateProducts.isEmpty()) {
            return Collections.emptyList();
        }

        return unvalidateProducts;
    }


    @Transactional
    public void createOrder(Map<String, Integer> productInCart, boolean status, String username) throws RuntimeException {
        List<String> products = validateProducts(productInCart);
        if (!products.isEmpty()) {
            throw new CustomIllegalArgumentException("You try to order more than we have in stock");
        }

        Order order = new Order(false);

        User user = userService.findByUsername(username);
//        order.addUser(user);
//        userService.save(user);
        order.setUser(user);
        this.save(order);

        Set<String> keys = productInCart.keySet();
        for (String productName : keys) {

            Product productFromDb = productService.findByName(productName);
            Integer productQuantityDb = productFromDb.getQuantity();
            Integer cartQuantity = productInCart.get(productName);

            productFromDb.setQuantity(productQuantityDb - cartQuantity);

            Cart cart = new Cart(cartQuantity, productFromDb);
            cart.addOrder(order);
//            cart.setOrder(order);

            //iznos ukupni za npr 2 narucena ista proizvoda: quantity tj. 2*cenaDin
            //treba logika za Sr/En
            cart.setAmountDin(cartQuantity * cart.getProduct().getAmountDin());
            cart.setAmountEuro(cartQuantity * cart.getProduct().getAmountEuro());
            order.saveQuantity();
            order.saveAmountDin();
            order.saveAmountEuro();
            cartService.save(cart);
        }

        userService.save(user);
    }

//
//        Iterator<Cart> cartIterator = carts.iterator();
//        while (cartIterator.hasNext()) {
////        for (Cart cart : cartIterator) {
//            Cart cart = cartIterator.next();
//
//            Product productFromDb = productService.findByName(cart.getProduct().getName());
//            Integer productQuantityDb = productFromDb.getQuantity();
//            Integer cartQuantity = cart.getQuantity();
//
//            productFromDb.setQuantity(productQuantityDb - cartQuantity);
//
////            cart.addOrder(order);
//            cart.setOrder(order);
//
//            //iznos ukupni za npr 2 narucena ista proizvoda: quantity tj. 2*cenaDin
//            //treba logika za Sr/En
//            cart.setAmountDin(cartQuantity * cart.getProduct().getAmountDin());
//            cart.setAmountEuro(cartQuantity * cart.getProduct().getAmountEuro());
//            order.saveQuantity();
//            order.saveAmountDin();
//            order.saveAmountEuro();
//            cartService.save(cart);
//            order.getCarts().add(cart);
////            cartService.save(cart);
//            this.save(order);
//        }
////        order.addUser(user);
//
////        this.save(order);
//        order.setUser(user);
//        this.save(order);
//        userService.save(user);
////        return this.save(order);
//    }

}
