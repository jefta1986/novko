package com.novko.internal.orders;

import com.novko.api.exception.CustomIllegalArgumentException;
import com.novko.api.request.OrderStatusModelRequest;
import com.novko.internal.cart.Cart;
import com.novko.internal.cart.CartService;
import com.novko.internal.products.Product;
import com.novko.internal.products.ProductService;
import com.novko.pdf.EmailService;
import com.novko.security.User;
import com.novko.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.*;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private ProductService productService;
    private CartService cartService;
    private UserService userService;
    private EmailService emailService;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCartService(CartService cartService) {
        this.cartService = cartService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Lazy
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }


//    @Autowired
//    public OrderService(OrderRepository orderRepository, ProductService productService, CartService cartService, UserService userService, EmailService emailServiceImpl) {
//        this.orderRepository = orderRepository;
//        this.productService = productService;
//        this.cartService = cartService;
//        this.userService = userService;
//        this.emailServiceImpl = emailServiceImpl;
//    }


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

//    @Transactional
//    public void delete(Order order) {
//        orderRepository.delete(order);
//    }

    @Transactional
    public void deleteById(Long id) {
        Order order = findById(id);
        List<Cart> carts = order.getCarts();
        for (Cart cart : carts) {
            Integer brojVracenihProizvoda = cart.getQuantity();
            Product product = cart.getProduct();
            product.setQuantity(product.getQuantity() + brojVracenihProizvoda);
            cart.removeProduct(product);
            productService.save(product);
            cartService.delete(cart);
        }
        order.removeUser(order.getUser());
        order.removeCarts();
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


    //Map<ProductCode, Quantity>
    @Transactional(readOnly = true)
    public List<Product> validateProducts(Map<String, Integer> productInCart) {
        List<Product> invalidProducts = new ArrayList<>();

        Set<String> keys = productInCart.keySet();
        for (String productCode : keys) {
            Product productFromDb = productService.findByCode(productCode);
            Integer productQuantityDb = productFromDb.getQuantity();
            Integer cartQuantity = productInCart.get(productCode);
            if (cartQuantity > productQuantityDb) {
                invalidProducts.add(productFromDb);
            }
        }

        if (invalidProducts.isEmpty()) {
            return Collections.emptyList();
        }

        return invalidProducts;
    }


    //Map<ProductCode, Quantity>
    @Transactional
    public OrderStatusModelRequest createOrder(Map<String, Integer> productInCart, boolean status, String username) throws MessagingException, CustomIllegalArgumentException {
        List<Product> products = validateProducts(productInCart); //ukoliko proizvodi ne postoje u magacinu
        OrderStatusModelRequest orderStatusModelResponse = new OrderStatusModelRequest();
        if (!products.isEmpty()) {
            return new OrderStatusModelRequest(Boolean.FALSE, products);
//            throw new CustomIllegalArgumentException("You try to order more than we have in stock");
        }

        Order order = new Order(false);

        User user = userService.findByUsername(username);
//        order.addUser(user);
//        userService.save(user);
        order.setUser(user);
        this.save(order);

        Set<String> keys = productInCart.keySet();
        for (String productCode : keys) {

            Product productFromDb = productService.findByCode(productCode);
            Integer productQuantityDb = productFromDb.getQuantity();
            Integer cartQuantity = productInCart.get(productCode);

            productFromDb.setQuantity(productQuantityDb - cartQuantity);

            Cart cart = new Cart(cartQuantity, productFromDb);
            cart.addOrder(order);
//
            if(user.getLanguage().equals("SR")) {
                cart.setAmountDin(cartQuantity * cart.getProduct().getAmountDin()); //amount_din za cart (quantity * product_price)
            } else if (user.getLanguage().equals("EN")) {
                cart.setAmountEuro(cartQuantity * cart.getProduct().getAmountEuro()); //amount_euro za cart
            }
            order.saveQuantity();

            if(user.getLanguage().equals("SR")) {
                order.saveAmountDin(); //amount_din za order (total amount)
            } else if (user.getLanguage().equals("EN")) {
                order.saveAmountEuro(); //amount_euro za order
            }
            cartService.save(cart);
        }

        userService.save(user);

        try {
            emailService.sendMessageWithAttachment(order);
        } catch (MessagingException e) {
            throw new CustomIllegalArgumentException("Problem with sending email");
        }

        return new OrderStatusModelRequest(Boolean.TRUE, null); //products umesto null
    }

    @Transactional(readOnly = true)
    public List<Order> findUserOrders(String username) {
        return orderRepository.findByLoggedUserUsername(username);
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
