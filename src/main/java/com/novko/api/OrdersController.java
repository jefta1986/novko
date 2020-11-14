package com.novko.api;

import java.security.Principal;
import java.util.List;

import com.novko.internal.cart.CartRepository;
import com.novko.internal.orders.OrderRepository;
import com.novko.internal.products.Product;
import com.novko.internal.products.ProductRepository;
import com.novko.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.novko.internal.cart.Cart;
import com.novko.internal.orders.Order;

@RestController
@RequestMapping("/rest/orders")
public class OrdersController {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserService userService;


    @Autowired
    public OrdersController(OrderRepository orderRepository, CartRepository cartRepository, ProductRepository productRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }


    @PostMapping(value = "")
    public ResponseEntity<String> save(@RequestBody List<Cart> carts, @RequestParam String username, @RequestParam String name, @RequestParam String surname,
                                       @RequestParam String phoneNumber, @RequestParam String country, @RequestParam String city, @RequestParam String address,
                                       @RequestParam String postalCode, @RequestParam(required = false) String description,
                                       Principal principal) {

//        Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!principal.getName().equals(username)) throw new RuntimeException("Username is not same with authenticated user!");

        for (Cart cart : carts) {
            Product productFromDb = productRepository.findByName(cart.getProduct().getName());
            Integer productQuantityDb = productFromDb.getQuantity();
            Integer cartQuantity = cart.getQuantity();
            if (cartQuantity > productQuantityDb) throw new RuntimeException("Product is anymore in stock: " + cart.getProduct().getName());
        }

        Order order = Order.factoryRecievingInfo(carts, false, name, surname, phoneNumber, country, city, address, postalCode, description);

        order.setUser(userService.findByUsername(username));
        orderRepository.save(order);

        for (Cart cart : carts) {
            Product productFromDb = productRepository.findByName(cart.getProduct().getName());
            Integer productQuantityDb = productFromDb.getQuantity();
            Integer cartQuantity = cart.getQuantity();

            productFromDb.setQuantity(productQuantityDb - cartQuantity);
            productRepository.save(productFromDb);  //update entity-merge

            cart.setOrder(order);

            //iznos ukupni za npr 2 narucena ista proizvoda: quantity tj. 2*cenaDin
            //treba logika za Sr/En
            cart.setAmountDin(cartQuantity*cart.getProduct().getAmountDin());
            cart.setAmountEuro(cartQuantity*cart.getProduct().getAmountEuro());

            cartRepository.save(cart);
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
        return new ResponseEntity<Order>(orderRepository.findById(id).get(), HttpStatus.OK);
    }


    @GetMapping(value = "")
    public ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<List<Order>>(orderRepository.findAll(), HttpStatus.OK);
    }


//    @GetMapping(value = "/unchecked")
//    public ResponseEntity<List<Order>> getOrdersUnchecked() {
//        return new ResponseEntity<List<Order>>(jpaOrdersRepository.getAllUnchecked(), HttpStatus.OK);
//    }

    @PostMapping(value = "/unchecked")
    public ResponseEntity<String> setOrdersUnchecked(@RequestParam("id") Long id) {
        Order order = orderRepository.getOne(id);
        order.setStatus(true);
        orderRepository.save(order); //update-merge
        return new ResponseEntity<String>("status of Order changed to checked", HttpStatus.OK);
    }

}
