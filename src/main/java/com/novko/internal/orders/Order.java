package com.novko.internal.orders;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novko.internal.cart.Cart;
import com.novko.security.User;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "T_ORDERS")
public class Order implements Serializable {

    private static final long serialVersionUID = -5551237878983548683L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	@SequenceGenerator(name = "seq_orders_gen", sequenceName = "seq_orders", allocationSize = 1, initialValue = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_orders_gen")
    @Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
    private Long id;


    @Column(name = "ORDER_DATE")
    private LocalDateTime orderDate;

    @Column(name = "TOTAL_AMOUNT_DIN")
    private Integer totalAmountDin;

    @Column(name = "TOTAL_AMOUNT_EURO")
    private Integer totalAmountEuro;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "STATUS")
    private Boolean status;

    //	@ManyToMany
//	@JoinTable(name = "T_CARTS", joinColumns = {@JoinColumn(name = "ORDERS_ID", referencedColumnName = "ID")}, inverseJoinColumns = {@JoinColumn(name = "PRODUCTS_ID", referencedColumnName = "ID")} )
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cart> carts;


    @Basic(fetch = FetchType.LAZY)
    @ManyToOne
//	@JoinColumn(name = "USER_ID")
    private User user;


    //ne treba da se izdvoji u novu tabelu
    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CITY")
    private String city;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @Column(name = "DESCRIPTION")
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;


    public Order() {
        this.carts = new ArrayList<>();
        this.orderDate = LocalDateTime.now();
        this.totalAmountDin = this.getTotalOrderPriceDin();
        this.totalAmountEuro = this.getTotalOrderPriceEuro();
        this.quantity = this.getNumberOfProducts();
    }

    public Order(List<Cart> carts) {
        this.carts = carts;
        this.orderDate = LocalDateTime.now();
        this.totalAmountDin = this.getTotalOrderPriceDin();
        this.totalAmountEuro = this.getTotalOrderPriceEuro();
        this.quantity = this.getNumberOfProducts();
    }


    public Order(List<Cart> carts, Boolean status, String name, String surname, String phoneNumber, String country, String city, String address, String postalCode, String description) {
        this.carts = carts;
        this.orderDate = LocalDateTime.now();
        this.totalAmountDin = this.getTotalOrderPriceDin();
        this.totalAmountEuro = this.getTotalOrderPriceEuro();
        this.quantity = this.getNumberOfProducts();
        this.status = status;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.city = city;
        this.address = address;
        this.postalCode = postalCode;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getTotalAmountDin() {
        return totalAmountDin;
    }

    public void setTotalAmountDin(Integer totalAmountDin) {
        this.totalAmountDin = totalAmountDin;
    }

    public Integer getTotalAmountEuro() {
        return totalAmountEuro;
    }

    public void setTotalAmountEuro(Integer totalAmountEuro) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    //METHODS

    @Transient
    @JsonIgnore
    public Integer getTotalOrderPriceDin() {

        int sum = 0;
        List<Cart> orderProducts = this.getCarts();

        for (Cart cart : orderProducts) {
            sum += cart.getQuantity() * cart.getProduct().getAmountDin(); //din
        }

        return sum;
    }


    @Transient
    @JsonIgnore
    public Integer getTotalOrderPriceEuro() {

        int sum = 0;
        List<Cart> orderProducts = this.getCarts();

        for (Cart cart : orderProducts) {
            sum += cart.getQuantity() * cart.getProduct().getAmountEuro(); //euro
        }

        return sum;
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


    public void addCarts(List<Cart> carts) {
        this.carts.addAll(carts);
    }


    public void saveQuantity() {
        this.quantity = getNumberOfProducts();
    }


    public static Order factory(List<Cart> carts) {
        return new Order(carts);
    }

    public static Order factoryRecievingInfo(List<Cart> carts, Boolean status, String name, String surname, String phoneNumber, String country, String city, String address, String postalCode, String description ) {
        return new Order(carts, status, name, surname, phoneNumber, country, city, address, postalCode, description);
    }

}
