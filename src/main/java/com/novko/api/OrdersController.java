package com.novko.api;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.novko.internal.products.JpaProductsRepository;
import com.novko.internal.products.Product;
import com.novko.security.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.novko.internal.cart.Cart;
import com.novko.internal.cart.JpaCartsRepository;
import com.novko.internal.orders.JpaOrdersRepository;
import com.novko.internal.orders.Order;

@RestController
@RequestMapping("/rest/orders")
public class OrdersController {

    private JpaOrdersRepository jpaOrdersRepository;
    private JpaCartsRepository jpaCartsRepository;
    private JpaUserRepository jpaUserRepository;
    private JpaProductsRepository jpaProductsRepository;

    @Autowired
    public void setJpaOrdersRepository(JpaOrdersRepository jpaOrdersRepository) {
        this.jpaOrdersRepository = jpaOrdersRepository;
    }

    @Autowired
    public void setJpaCartsRepository(JpaCartsRepository jpaCartsRepository) {
        this.jpaCartsRepository = jpaCartsRepository;
    }

    @Autowired
    public void setJpaUserRepository(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Autowired
    public void setJpaProductsRepository(JpaProductsRepository jpaProductsRepository) {
        this.jpaProductsRepository = jpaProductsRepository;
    }



    @PostMapping(value = "")
    public ResponseEntity<String> save(@RequestBody List<Cart> carts, @RequestParam String username, Principal principal) {

//        Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!principal.getName().equals(username)) throw new RuntimeException("Username is not same with authenticated user!");

        for (Cart cart : carts) {
            Product productFromDb = jpaProductsRepository.getByName(cart.getProduct().getName());
            Integer productQuantityDb = productFromDb.getQuantity();
            Integer cartQuantity = cart.getQuantity();
            if (cartQuantity > productQuantityDb) throw new RuntimeException("Product is anymore in stock: " + cart.getProduct().getName());
        }

        Order order = Order.factory(carts);

        order.setUser(jpaUserRepository.findByUsername(username).get());
        jpaOrdersRepository.save(order);

        for (Cart cart : carts) {
            Product productFromDb = jpaProductsRepository.getByName(cart.getProduct().getName());
            Integer productQuantityDb = productFromDb.getQuantity();
            Integer cartQuantity = cart.getQuantity();

            productFromDb.setQuantity(productQuantityDb - cartQuantity);
            jpaProductsRepository.update(productFromDb);

            cart.setOrder(order);

            jpaCartsRepository.save(cart);
        }

        return new ResponseEntity<String>("order saved", HttpStatus.OK);
    }

    //preko sesije (meni je potrebno za testiranje)
//    @PostMapping(value = "")
//    public ResponseEntity<String> save(HttpSession session, Principal principal) {
//
//        List<Cart> carts = (List<Cart>) session.getAttribute("cart");
//
//        for (Cart cart : carts) {
//            Product productFromDb = jpaProductsRepository.getByName(cart.getProduct().getName());
//            Integer productQuantityDb = productFromDb.getQuantity();
//            Integer cartQuantity = cart.getQuantity();
//            if (cartQuantity > productQuantityDb)
//                throw new RuntimeException("Product is anymore in stock: " + cart.getProduct().getName());
//        }
//
//        Order order = Order.factory(carts);
//
//        order.setUser(jpaUserRepository.findByUsername(principal.getName()).get());
//        jpaOrdersRepository.save(order);
//
//        for (Cart cart : carts) {
//            Product productFromDb = jpaProductsRepository.getByName(cart.getProduct().getName());
//            Integer productQuantityDb = productFromDb.getQuantity();
//            Integer cartQuantity = cart.getQuantity();
//
//            productFromDb.setQuantity(productQuantityDb - cartQuantity);
//            jpaProductsRepository.update(productFromDb);
//
//            cart.setOrder(order);
//
//            jpaCartsRepository.save(cart);
//        }
//
//        return new ResponseEntity<String>("order saved", HttpStatus.OK);
//    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return new ResponseEntity<Order>(jpaOrdersRepository.get(id), HttpStatus.OK);
    }


    @GetMapping(value = "")
    public ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<List<Order>>(jpaOrdersRepository.getAll(), HttpStatus.OK);
    }

}
