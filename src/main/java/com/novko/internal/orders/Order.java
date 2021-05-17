package com.novko.internal.orders;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novko.internal.cart.Cart;
import com.novko.security.User;

@Entity
@Table(name = "T_ORDERS")
public class Order implements Serializable {

    private static final long serialVersionUID = -5551237878983548683L;

    private static AtomicLong racunBroj = new AtomicLong(1);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	@SequenceGenerator(name = "seq_orders_gen", sequenceName = "seq_orders", allocationSize = 1, initialValue = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_orders_gen")
    @Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
    private Long id;


    @Column(name = "ORDER_DATE")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private OffsetDateTime orderDate;
//    private LocalDateTime orderDate;

    @Column(name = "TOTAL_AMOUNT_DIN")
    private Double totalAmountDin;

    @Column(name = "TOTAL_AMOUNT_EURO")
    private Double totalAmountEuro;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "STATUS")
    private Boolean status;

    //	@ManyToMany
//	@JoinTable(name = "T_CARTS", joinColumns = {@JoinColumn(name = "ORDERS_ID", referencedColumnName = "ID")}, inverseJoinColumns = {@JoinColumn(name = "PRODUCTS_ID", referencedColumnName = "ID")} )
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<Cart> carts = new ArrayList<>();


//    @JsonIgnore
    @Basic(fetch = FetchType.LAZY)
    @ManyToOne
//	@JoinColumn(name = "USER_ID")
    private User user;


    //ne treba da se izdvoji u novu tabelu
//    @Column(name = "NAME")
//    private String name;
//
//    @Column(name = "SURNAME")
//    private String surname;
//
//    @Column(name = "PHONE_NUMBER")
//    private String phoneNumber;
//
//    @Column(name = "COUNTRY")
//    private String country;
//
//    @Column(name = "CITY")
//    private String city;
//
//    @Column(name = "ADDRESS")
//    private String address;
//
//    @Column(name = "POSTAL_CODE")
//    private String postalCode;
//
//    @Column(columnDefinition = "TEXT", name = "DESCRIPTION")
////    @Column(name = "DESCRIPTION")
////    @Lob
////    @Type(type = "org.hibernate.type.TextType")
//    private String description;

    public Order() {
        this.orderDate = OffsetDateTime.now(ZoneOffset.UTC);
    }

    public Order(List<Cart> carts) {
        this.carts = carts;
        this.orderDate = OffsetDateTime.now(ZoneOffset.UTC);
    }


    public Order(Boolean status) {
        this.orderDate = OffsetDateTime.now(ZoneOffset.UTC);
        this.status = status;
//        this.name = name;
//        this.surname = surname;
//        this.phoneNumber = phoneNumber;
//        this.country = country;
//        this.city = city;
//        this.address = address;
//        this.postalCode = postalCode;
//        this.description = description;
    }


    //METHODS
    public void addUser(User user) {
        this.user = user;
        user.getOrders().add(this);
    }

    public void removeUser(User user) {
        this.user = null;
        user.getOrders().remove(this);
    }

//    public void addCart(Cart cart){
//        this.carts.add(cart);
//        cart.setOrder(this);
//    }
//
//    public void removeCart(Cart cart){
//        this.carts.remove(cart);
//        cart.setOrder(null);
//    }

    //parametar ubaci enum da bi bila jedna metoda getTotalOrderPrice(priceTypeEnum)
    @Transient
    @JsonIgnore
    public Double getTotalOrderPriceDin() {
        int sum = 0;
        List<Cart> carts = this.carts;

        for (Cart cart : carts) {
            sum += cart.getQuantity() * cart.getAmountDin(); //din
        }

        return sum - sum * getUser().getRabat();
    }


    @Transient
    @JsonIgnore
    public Double getTotalOrderPriceEuro() {
        int sum = 0;
        List<Cart> carts = this.carts;

        for (Cart cart : carts) {
            sum += cart.getQuantity() * cart.getAmountEuro(); //euro
        }

        return sum - sum * getUser().getRabat();
    }


    @Transient
    @JsonIgnore
    public Integer getNumberOfProducts() {
        int sum = 0;
        for (Cart cart : this.carts) {
            sum += cart.getQuantity();
        }

        return sum;
    }

    //ADD CARTS FROM SESSION STORAGE List<Cart>
    public void addCarts(List<Cart> carts) {
        this.carts.addAll(carts);
        Iterator<Cart> cartIterator = carts.iterator();
        while (cartIterator.hasNext()) {
            Cart cart = cartIterator.next();
            cart.setOrder(this);
        }
    }

    public void removeCarts() {
        this.carts.clear();
        Iterator<Cart> cartIterator = carts.iterator();
        while (cartIterator.hasNext()) {
            Cart cart = cartIterator.next();
            cart.setOrder(null);
        }
    }

//    public void removeCart(Cart cart) {
//        this.carts.remove(cart);
//        cart.setOrder(null);
//
//    }


    public void saveQuantity() {
        this.quantity = getNumberOfProducts();
    }

    public void saveAmountDin() {
        this.totalAmountDin = getTotalOrderPriceDin();
    }

    public void saveAmountEuro() {
        this.totalAmountEuro = getTotalOrderPriceEuro();
    }


    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(OffsetDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Double getTotalAmountDin() {
        return totalAmountDin;
    }

    public void setTotalAmountDin(Double totalAmountDin) {
        this.totalAmountDin = totalAmountDin;
    }

    public Double getTotalAmountEuro() {
        return totalAmountEuro;
    }

    public void setTotalAmountEuro(Double totalAmountEuro) {
        this.totalAmountEuro = totalAmountEuro;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts.addAll(carts);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public static long getRacunBroj() {
        if (racunBroj.get() == Long.MAX_VALUE) {
            racunBroj = new AtomicLong(1);
        }
        return racunBroj.getAndIncrement();
    }

    //    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPostalCode() {
//        return postalCode;
//    }
//
//    public void setPostalCode(String postalCode) {
//        this.postalCode = postalCode;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getSurname() {
//        return surname;
//    }
//
//    public void setSurname(String surname) {
//        this.surname = surname;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }

}
