package com.novko.internal.cart;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novko.internal.orders.Order;
import com.novko.internal.products.Product;

@Entity
@Table(name = "T_CARTS")
public class Cart implements Serializable{

	private static final long serialVersionUID = -3773696614929466880L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@SequenceGenerator(name = "seq_carts_gen", sequenceName = "seq_carts", allocationSize = 1, initialValue = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_carts_gen")
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	private Long id;
	
	
	@Column(name = "QUANTITY")
	private Integer quantity;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ORDERS_ID")
	private Order order;

	@Basic(fetch =  FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name = "PRODUCTS_ID")
	private Product product;

	@Column(name = "AMOUNT_DIN")
	private Integer amountDin;

	@Column(name = "AMOUNT_EURO")
	private Integer amountEuro;

	@Column(name = "UKUPNO")
	private Double ukupno;

	@Column(name = "PDV")
	private Double pdv;

	
	
	public Cart() {}


	public Cart(Integer quantity, Product product) {
		this.quantity = quantity;
		this.product = product;
	}
	

	public Cart(Integer quantity, Order order, Product product) {
		this.quantity = quantity;
		this.order = order;
		this.product = product;
	}

	public void addOrder(Order order){
		this.order = order;
		order.getCarts().add(this);
	}

	public void removeOrder(Order order){
		this.order = null;
		order.getCarts().remove(this);
	}

	public void removeProduct(Product product){
		this.product = null;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Order getOrder() {
		return order;
	}


	public void setOrder(Order order) {
		this.order = order;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getAmountDin() {
		return amountDin;
	}

	public void setAmountDin(Integer amountDin) {
		this.amountDin = amountDin;
	}

	public Integer getAmountEuro() {
		return amountEuro;
	}

	public void setAmountEuro(Integer amountEuro) {
		this.amountEuro = amountEuro;
	}

	public Double getUkupno() {
		return ukupno;
	}

	public void setUkupno(Double ukupno) {
		this.ukupno = ukupno;
	}

	public Double getPdv() {
		return pdv;
	}

	public void setPdv(Double pdv) {
		this.pdv = pdv;
	}


	//	public void addProduct(Products product) {
//		Carts cart = new Carts();
//		Integer quantity = product.getQuantity();
//		
//		cart.setProduct(product);
//		cart.setQuantity(--quantity);
//	}
//	

}
