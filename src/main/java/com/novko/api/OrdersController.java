package com.novko.api;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import com.novko.api.mapper.OrderMapper;
import com.novko.api.response.OrderResponse;
import com.novko.internal.cart.CartService;
import com.novko.internal.orders.OrderService;
import com.novko.internal.products.ProductService;
import com.novko.security.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


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

    //Map<productCode, Quantity> dolazi sa frontenda
    @PostMapping(value = "")
    @ApiOperation(value = "Save Order and send email with reciept(pdf file) to customer")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> save(@RequestBody Map<String, Integer> productsInCart, @RequestParam String username, Principal principal) {
//        Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!principal.getName().equals(username))
            throw new RuntimeException("Username is not same with authenticated user!");

        orderService.createOrder(productsInCart, false, username);

        return new ResponseEntity<String>("Order saved successfully!", HttpStatus.OK);
    }


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

//    @PostMapping(value = "")
//    @ApiOperation(value = "Save Order and send email with reciept(pdf file) to customer")
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<String> save(@RequestBody List<Cart> carts, @RequestParam String username, @RequestParam String name, @RequestParam String surname,
//                                       @RequestParam String phoneNumber, @RequestParam String country, @RequestParam String city, @RequestParam String address,
//                                       @RequestParam String postalCode, @RequestParam(required = false) String description,
//                                       Principal principal) {
//
////        Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if(!principal.getName().equals(username)) throw new RuntimeException("Username is not same with authenticated user!");
//
//        orderService.createOrder(carts,false, username, name, surname, phoneNumber, country, city, address, postalCode, description);
//
//
////        for (Cart cart : carts) {
////            Product productFromDb = productService.findByName(cart.getProduct().getName());
////            Integer productQuantityDb = productFromDb.getQuantity();
////            Integer cartQuantity = cart.getQuantity();
////            if (cartQuantity > productQuantityDb) throw new RuntimeException("Product is anymore in stock: " + cart.getProduct().getName());
////        }
////
////        Order order = new Order(carts, false, name, surname, phoneNumber, country, city, address, postalCode, description);
////
////        order.setUser(userService.findByUsername(username));
////        orderService.save(order);
////
////        for (Cart cart : carts) {
////            Product productFromDb = productService.findByName(cart.getProduct().getName());
////            Integer productQuantityDb = productFromDb.getQuantity();
////            Integer cartQuantity = cart.getQuantity();
////
////            productFromDb.setQuantity(productQuantityDb - cartQuantity);
////            productService.save(productFromDb);  //update entity-merge
////
////            cart.setOrder(order);
////
////            //iznos ukupni za npr 2 narucena ista proizvoda: quantity tj. 2*cenaDin
////            //treba logika za Sr/En
////            cart.setAmountDin(cartQuantity*cart.getProduct().getAmountDin());
////            cart.setAmountEuro(cartQuantity*cart.getProduct().getAmountEuro());
////
////            cartService.save(cart);
////        }
//
//        return new ResponseEntity<String>("Order saved successfully!", HttpStatus.OK);
//    }


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



    //izmeni kasnije da user ne moze da vidi
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get Order By Id")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public OrderResponse getOrderById(@PathVariable Long id) {
        return OrderMapper.INSTANCE.toDto(orderService.findById(id));
    }

    @GetMapping(value = "")
    @ApiOperation(value = "Get All Orders")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<OrderResponse> getOrders() {
        return OrderMapper.INSTANCE.listToDto(orderService.findAll());
    }

    @GetMapping(value = "/unchecked")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<OrderResponse> getOrdersUnchecked() {
        return OrderMapper.INSTANCE.listToDto(orderService.findByStatusFalse());
    }

    @PatchMapping(value = "/unchecked")
    @ApiOperation(value = "Update Order status=true")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public OrderResponse setOrderStatusTrue(@RequestParam("id") Long id) {
        return OrderMapper.INSTANCE.toDto(orderService.updateStatusToTrue(id));
    }

    @DeleteMapping(value = "")
    @ApiOperation(value = "Delete Order By Id")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOrderById(@RequestParam("id") Long id) {
        orderService.deleteById(id);
    }

}
