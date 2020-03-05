package com.novko.api;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.novko.internal.products.JpaProductsRepository;
import com.novko.internal.products.Product;
import com.novko.security.JpaUserDao;
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
    private JpaUserDao jpaUserDaoImpl;
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
    public void setJpaUserDaoImpl(JpaUserDao jpaUserDaoImpl) {
        this.jpaUserDaoImpl = jpaUserDaoImpl;
    }

    @Autowired
    public void setJpaProductsRepository(JpaProductsRepository jpaProductsRepository) {
        this.jpaProductsRepository = jpaProductsRepository;
    }


    @PostMapping(value = "")
    public ResponseEntity<String> save(@RequestBody List<Cart> carts, @RequestParam String username,
                                       @RequestParam Boolean status, @RequestParam String name, @RequestParam String surname,
                                       @RequestParam String phoneNumber, @RequestParam String country, @RequestParam String city, @RequestParam String address,
                                       @RequestParam String postalCode, @RequestParam String description,
                                       Principal principal) {

//        Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!principal.getName().equals(username)) throw new RuntimeException("Username is not same with authenticated user!");

        for (Cart cart : carts) {
            Product productFromDb = jpaProductsRepository.getByName(cart.getProduct().getName());
            Integer productQuantityDb = productFromDb.getQuantity();
            Integer cartQuantity = cart.getQuantity();
            if (cartQuantity > productQuantityDb) throw new RuntimeException("Product is anymore in stock: " + cart.getProduct().getName());
        }

        Order order = Order.factoryRecievingInfo(carts, status, name, surname, phoneNumber, country, city, address, postalCode, description);

        order.setUser(jpaUserDaoImpl.findByUsername(username));
        jpaOrdersRepository.save(order);

        for (Cart cart : carts) {
            Product productFromDb = jpaProductsRepository.getByName(cart.getProduct().getName());
            Integer productQuantityDb = productFromDb.getQuantity();
            Integer cartQuantity = cart.getQuantity();

            productFromDb.setQuantity(productQuantityDb - cartQuantity);
            jpaProductsRepository.update(productFromDb);

            cart.setOrder(order);

            //iznos ukupni za npr 2 narucena ista proizvoda: quantity tj. 2*cenaDin
            //treba logika za Sr/En
            cart.setAmountDin(cartQuantity*cart.getProduct().getAmountDin());
            cart.setAmountEuro(cartQuantity*cart.getProduct().getAmountEuro());

            jpaCartsRepository.save(cart);
        }

        return new ResponseEntity<String>("order saved", HttpStatus.OK);
    }


//    @PostMapping(value = "")
//    public ResponseEntity<String> save(@RequestBody List<Cart> carts, @RequestParam String username, Principal principal) {
//
////        Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if(!principal.getName().equals(username)) throw new RuntimeException("Username is not same with authenticated user!");
//
//        for (Cart cart : carts) {
//            Product productFromDb = jpaProductsRepository.getByName(cart.getProduct().getName());
//            Integer productQuantityDb = productFromDb.getQuantity();
//            Integer cartQuantity = cart.getQuantity();
//            if (cartQuantity > productQuantityDb) throw new RuntimeException("Product is anymore in stock: " + cart.getProduct().getName());
//        }
//
//        Order order = Order.factory(carts);
//
//        order.setUser(jpaUserDaoImpl.findByUsername(username));
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
//            //iznos ukupni za npr 2 narucena ista proizvoda: quantity tj. 2*cenaDin
//            //treba logika za Sr/En
//            cart.setAmountDin(cartQuantity*cart.getProduct().getAmountDin());
//            cart.setAmountEuro(cartQuantity*cart.getProduct().getAmountEuro());
//
//            jpaCartsRepository.save(cart);
//        }
//
//        return new ResponseEntity<String>("order saved", HttpStatus.OK);
//    }




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


    @GetMapping(value = "/unchecked")
    public ResponseEntity<List<Order>> getOrdersUnchecked() {
        return new ResponseEntity<List<Order>>(jpaOrdersRepository.getAllUnchecked(), HttpStatus.OK);
    }

    @PostMapping(value = "/unchecked")
    public ResponseEntity<String> setOrdersUnchecked(@RequestParam("id") Long id) {
        Order order = jpaOrdersRepository.get(id);
        order.setStatus(true);
        jpaOrdersRepository.update(order);
        return new ResponseEntity<String>("status of Order changed to checked", HttpStatus.OK);
    }

}
