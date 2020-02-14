package com.novko.pdf;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.novko.internal.cart.Cart;
import com.novko.security.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderJasperDto implements Serializable {

	private static final long serialVersionUID = -5551237878983548683L;

	private Long id;

	private LocalDateTime orderDate;

	private Integer totalAmount;


	private Integer quantity;

	private List<Cart> carts;





	public OrderJasperDto(){
	}








}
