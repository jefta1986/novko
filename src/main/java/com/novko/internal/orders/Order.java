package com.novko.internal.orders;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.novko.internal.cart.Cart;
import com.novko.security.User;

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
	
	
	@Column(name = "TOTAL_AMOUNT")
	private Integer totalAmount;
	
	
	@Column(name = "QUANTITY")
	private Integer quantity;

//	@ManyToMany
//	@JoinTable(name = "T_CARTS", joinColumns = {@JoinColumn(name = "ORDERS_ID", referencedColumnName = "ID")}, inverseJoinColumns = {@JoinColumn(name = "PRODUCTS_ID", referencedColumnName = "ID")} )
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Cart> carts;


	@Basic(fetch =  FetchType.LAZY)
	@ManyToOne
//	@JoinColumn(name = "USER_ID")
	private User user;


	
	public Order(){
		this.carts = new ArrayList<>();
		this.orderDate = LocalDateTime.now();
		this.totalAmount = this.getTotalOrderPriceDin();
		this.quantity = this.getNumberOfProducts();
	}

	public Order(List<Cart> carts) {
		this.carts = carts;
		this.orderDate = LocalDateTime.now();
		this.totalAmount = this.getTotalOrderPriceDin();
		this.quantity = this.getNumberOfProducts();
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

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	
//	public List<Cart> getCarts() {
//		return carts;
//	}


	public List<Cart> getCarts() {
		return Collections.unmodifiableList(carts);
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


//METHODS

	@Transient
	@JsonIgnore
	public Integer getTotalOrderPriceDin() {
		int sum = 0;
		List<Cart> orderProducts = this.getCarts();
		for (Cart cart : orderProducts) {
			sum += cart.getQuantity()*cart.getProduct().getAmountDin(); //mora logika za din i eure
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
		this.quantity=getNumberOfProducts();
	}

	
	public static Order factory(List<Cart> carts) {
		return new Order(carts);
	}


//	@Override
//	public String toString() {
//		return "Order [id=" + id + ", orderDate=" + orderDate + ", totalAmount=" + totalAmount + ", quantity="
//				+ quantity + ", carts=" + carts + "]";
//	}


	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", orderDate=" + orderDate +
				", totalAmount=" + totalAmount +
				", quantity=" + quantity +
				", carts=" + carts +
				", user=" + user +
				'}';
	}
}
