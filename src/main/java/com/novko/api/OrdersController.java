package com.novko.api;

import java.security.Principal;
import java.util.List;

import com.novko.internal.cart.CartService;
import com.novko.internal.orders.OrderService;
import com.novko.internal.products.ProductService;
import com.novko.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.novko.internal.cart.Cart;
import com.novko.internal.orders.Order;

@RestController
@RequestMapping("/rest/orders")
public class OrdersController {

    private final OrderService orderService;
    private final CartService cartService;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public OrdersController(OrderService orderService, CartService cartService, ProductService productService, UserService userService) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.productService = productService;
        this.userService = userService;
    }


    @PostMapping(value = "")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
    public ResponseEntity<String> save(@RequestBody List<Cart> carts, @RequestParam String username, @RequestParam String name, @RequestParam String surname,
                                       @RequestParam String phoneNumber, @RequestParam String country, @RequestParam String city, @RequestParam String address,
                                       @RequestParam String postalCode, @RequestParam(required = false) String description,
                                       Principal principal) {

//        Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!principal.getName().equals(username)) throw new RuntimeException("Username is not same with authenticated user!");

        orderService.createOrder(carts,false, username, name, surname, phoneNumber, country, city, address, postalCode, description);


//        for (Cart cart : carts) {
//            Product productFromDb = productService.findByName(cart.getProduct().getName());
//            Integer productQuantityDb = productFromDb.getQuantity();
//            Integer cartQuantity = cart.getQuantity();
//            if (cartQuantity > productQuantityDb) throw new RuntimeException("Product is anymore in stock: " + cart.getProduct().getName());
//        }
//
//        Order order = new Order(carts, false, name, surname, phoneNumber, country, city, address, postalCode, description);
//
//        order.setUser(userService.findByUsername(username));
//        orderService.save(order);
//
//        for (Cart cart : carts) {
//            Product productFromDb = productService.findByName(cart.getProduct().getName());
//            Integer productQuantityDb = productFromDb.getQuantity();
//            Integer cartQuantity = cart.getQuantity();
//
//            productFromDb.setQuantity(productQuantityDb - cartQuantity);
//            productService.save(productFromDb);  //update entity-merge
//
//            cart.setOrder(order);
//
//            //iznos ukupni za npr 2 narucena ista proizvoda: quantity tj. 2*cenaDin
//            //treba logika za Sr/En
//            cart.setAmountDin(cartQuantity*cart.getProduct().getAmountDin());
//            cart.setAmountEuro(cartQuantity*cart.getProduct().getAmountEuro());
//
//            cartService.save(cart);
//        }

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
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return new ResponseEntity<Order>(orderService.findById(id), HttpStatus.OK);
    }


    @GetMapping(value = "")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
    public ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<List<Order>>(orderService.findAll(), HttpStatus.OK);
    }


//    @GetMapping(value = "/unchecked")
//    public ResponseEntity<List<Order>> getOrdersUnchecked() {
//        return new ResponseEntity<List<Order>>(jpaOrdersRepository.getAllUnchecked(), HttpStatus.OK);
//    }

    @PostMapping(value = "/unchecked")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or isAnonymous()")
    public ResponseEntity<String> setOrdersUnchecked(@RequestParam("id") Long id) {
        Order order = orderService.findById(id);
        order.setStatus(true);
        orderService.save(order); //update-merge
        return new ResponseEntity<String>("status of Order changed to checked", HttpStatus.OK);
    }

}
