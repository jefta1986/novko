package com.novko.api;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.novko.api.mapper.OrderMapper;
import com.novko.api.mapper.OrderStatusModelMapper;
import com.novko.api.request.OrderStatusModelRequest;
import com.novko.api.response.OrderResponse;
import com.novko.api.response.OrderStatusModelResponse;
import com.novko.internal.cart.CartService;
import com.novko.internal.orders.Order;
import com.novko.internal.orders.OrderService;
import com.novko.internal.products.ProductService;
import com.novko.security.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;


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
    @ApiOperation(value = "USER: Save Order and send Email with Receipt (PDF file) to USER")
    @PreAuthorize("hasRole('USER')")
    public OrderStatusModelResponse save(@RequestBody Map<String, Integer> productsInCart, @RequestParam String username, Principal principal) {
//        Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!principal.getName().equals(username)) {
            throw new RuntimeException("Username is not same with authenticated user!");
        }

        OrderStatusModelRequest orderStatusModelRequest;
        try {
            orderStatusModelRequest = orderService.createOrder(productsInCart, false, username);
        }catch (MessagingException e) {
            throw new RuntimeException("Problem with sending email");
        }

        return OrderStatusModelMapper.INSTANCE.toResponse(orderStatusModelRequest);
    }


    //PROVERI: ulogovani USER moze da vidi order ukoliko je kreiran od strane tog istog usera!!!
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "ADMIN and USER: Get Order By Id")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public OrderResponse getOrderById(@PathVariable Long id, Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String role = authentication.getAuthorities().stream().map(a -> ((GrantedAuthority) a).getAuthority()).collect(Collectors.joining(""));

        if (role.equals("ROLE_ADMIN")) {
            return OrderMapper.INSTANCE.toDto(orderService.findById(id));
        } else {
            String username = principal.getName();
            List<Order> loggedUserOrders = orderService.findUserOrders(username);
            for (Order order : loggedUserOrders) {
                if( username.equals(order.getUser().getUsername()) && id.equals(order.getId()) ) {
                    return OrderMapper.INSTANCE.toDto(orderService.findById(id));
                }
            }
        }
        return null;
    }

    @GetMapping(value = "")
    @ApiOperation(value = "ADMIN: Get All Orders")
    @PreAuthorize("hasRole('ADMIN')")
    public List<OrderResponse> getOrders() {
        return OrderMapper.INSTANCE.listToDto(orderService.findAll());
    }


    @GetMapping(value = "/unchecked")
    @ApiOperation(value = "ADMIN: Get all UNCHECKED Orders")
    @PreAuthorize("hasRole('ADMIN')")
    public List<OrderResponse> getOrdersUnchecked() {
        return OrderMapper.INSTANCE.listToDto(orderService.findByStatusFalse());
    }
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication instanceof AnonymousAuthenticationToken) {
//            return Collections.emptyList();
//        }
//
//        String role = authentication.getAuthorities().stream().map(a -> ((GrantedAuthority) a).getAuthority()).collect(Collectors.joining(""));
//
//        if (role.equals("ROLE_ADMIN")) {
//            return OrderMapper.INSTANCE.listToDto(orderService.findByStatusFalse());
//        } else {
//            return Collections.emptyList();
//        }

    @PatchMapping(value = "/unchecked")
    @ApiOperation(value = "ADMIN: Update Order status=true")
    @PreAuthorize("hasRole('ADMIN')")
    public OrderResponse setOrderStatusTrue(@RequestParam("id") Long id) {
        return OrderMapper.INSTANCE.toDto(orderService.updateStatusToTrue(id));
    }


    //BITNO: da li USER moze da obrise svoj Order??????
    @DeleteMapping(value = "")
    @ApiOperation(value = "ADMIN: Delete Order by Id")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteOrderById(@RequestParam("id") Long id) {
        orderService.deleteById(id);
    }


    // Ulogovani USER da moze da pogleda svoje Ordere
    @GetMapping(value = "/user")
    @ApiOperation(value = "USER: Get logged-USER Orders by username")
    @PreAuthorize("hasRole('USER')")
    public List<OrderResponse> getUserOrders(@RequestParam String username) {
        return OrderMapper.INSTANCE.listToDto(orderService.findUserOrders(username));
    }


}
