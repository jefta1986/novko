package com.novko.api;

import java.security.Principal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.novko.api.mapper.OrderMapper;
import com.novko.api.mapper.OrderStatusModelMapper;
import com.novko.api.request.*;
import com.novko.api.response.OrderResponse;
import com.novko.api.response.OrderStatusModelResponse;
import com.novko.internal.cart.CartService;
import com.novko.internal.orders.Order;
import com.novko.internal.orders.OrderService;
import com.novko.internal.products.ProductService;
import com.novko.internal.products.QProduct;
import com.novko.security.UserService;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    //ne koristi se. uradi!!!
//    @GetMapping(value = "")
//    @ApiOperation(value = "ADMIN: Get All Orders")
//    @PreAuthorize("hasRole('ADMIN')")
//    public List<OrderResponse> getOrders() {
//        return OrderMapper.INSTANCE.listToDto(orderService.findAll());
//    }


    //order uradi!!!!
    @GetMapping(value = "/filtered")
    @ApiOperation(value = "ADMIN: Get All Orders Filtered - return Page<Order> object, date-time pattern (2020-12-18T00:00)")
    @PreAuthorize("hasRole('ADMIN') or isAnonymous()")  //izbaci anonymous
    public Page<OrderResponse> getOrdersFiltered(@RequestParam(name = "status", required = false) Boolean status,
                                                 @RequestParam(name = "userPart", required = false) String userPart,
//                                                 @RequestParam(name = "fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
//                                                 @RequestParam(name = "toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate,
                                                 @RequestParam(name = "fromDate", required = false) String fromDate,
                                                 @RequestParam(name = "toDate", required = false) String toDate,
                                                 @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(name = "size", defaultValue = "12") Integer size,
                                                 @RequestParam(name = "sort", required = true) OrderSortProperty sort,
                                                 @RequestParam(name = "direction", defaultValue = "ASC") SortDirection direction) {

        OrderFilter orderFilter = new OrderFilter();
        if (status != null) {
            orderFilter.setStatus(status);
        }
        if (userPart != null && !userPart.isEmpty()) {
            orderFilter.setUserPart(userPart);
        }
        if (fromDate != null && !fromDate.isEmpty()) {
            OffsetDateTime from = OffsetDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(fromDate)), ZoneId.of("UTC").normalized());

            orderFilter.setFromDate(from);
        }
        if (toDate != null && !toDate.isEmpty()) {
            OffsetDateTime to = OffsetDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(toDate)), ZoneId.of("UTC").normalized());

            orderFilter.setToDate(to);
        }

        Query query = new QueryBuilder()
                .setPage(page)
                .setSize(size)
                .setSortDirection(direction.name())
                .setSortProperty(sort.getField())
                .setFilter(orderFilter)
                .createQuery();

        return OrderMapper.INSTANCE.pageToDto(orderService.findAllOrFiltered(query));
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


    //NE RADI!!! NE TREBA DA PRIKAZE I USERA SA ORDEROM!!
    // Ulogovani USER da moze da pogleda svoje Ordere
    @GetMapping(value = "/user")
    @ApiOperation(value = "USER: Get logged-USER Orders by username")
    @PreAuthorize("hasRole('USER')")
    public List<OrderResponse> getUserOrders(@RequestParam String username) {
        return OrderMapper.INSTANCE.listToDto(orderService.findUserOrders(username));
    }

    private Predicate buildPredicate(ProductFilter filter) {
        List<Predicate> expressions = new LinkedList<>();
        if (filter != null) {
            if (filter.isActive() != null) {
                expressions.add(
                        QProduct.product.enabled.eq(filter.isActive()));
            }
            if (filter.getNamePart() != null && !filter.getNamePart().trim().isEmpty()) {
                expressions.add(
                        QProduct.product.name.containsIgnoreCase(filter.getNamePart()));
            }
            if (filter.getCodePart() != null && !filter.getCodePart().trim().isEmpty()) {
                expressions.add(
                        QProduct.product.code.containsIgnoreCase(filter.getCodePart()));
            }
        }
        return ExpressionUtils.allOf(expressions);
    }

}
